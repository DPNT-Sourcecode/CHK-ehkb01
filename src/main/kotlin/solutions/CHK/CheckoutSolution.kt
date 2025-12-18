package solutions.CHK

import kotlin.collections.mutableMapOf
import kotlin.collections.set


/*** *
 * checkout(string) -> integer
 *  - param[0] = a string containing the SKUs of all the products in the basket
 *  - @return = an integer representing the total checkout value of the items
 *
 *  NOTE: Offers involving multiple items always give a better discount than offers containing fewer items.
 *  Normally E costs 40, but if you buy 2 of Es you will get B free.
 */
class CheckoutSolution {
    fun checkout(skus: String): Int {
        //calculate total price of items in the skus string
        var totalPrice = 0

        // Create a map of SKU to quantity
        val skusMap = mutableMapOf<String, Int>()
        skus.split("").filter { it.isNotEmpty() }.groupingBy { it }.eachCount().forEach { (sku, quantity) ->
            skusMap[sku] = quantity
        }

        // 1st pass to handle free item offers
        handleFreeItemOffers(skusMap)

        // 2nd pass to calculate total price
        for ((sku, quantity) in skusMap) {
            println("$sku - $quantity")
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                //Always check the biggest offer first, if quantity is less or has remaining items, check the next offer
                var remainingQuantity = quantity
                val sortedOffers = item.specialOffers?.sortedByDescending { it.requiredQuantity } ?: emptyList()
                for (offer in sortedOffers) {
                    // This while loop does not work for group discounts, as the requiredQuantity is spread across multiple SKUs
                    while (remainingQuantity >= offer.requiredQuantity) {
                        //println("remainingQuantity: $remainingQuantity for SKU: $sku applying offer: $offer")
                        when (offer.offerDetail) {
                            is OfferType.OfferDetail.PriceOffer -> {
                                totalPrice += offer.offerDetail.offerPrice
                            }

                            is OfferType.OfferDetail.FreeItemOffer -> {
                                totalPrice += item.price * offer.requiredQuantity
                            }
                            else -> break // Group offers are handled separately
                        }
                        remainingQuantity -= offer.requiredQuantity
                    }
                    val priceOfGroupOffer = handleGroupOffers(skusMap, sku, offer)
                    totalPrice += priceOfGroupOffer
                    // Update remaining quantity after group offer processing
                    if (priceOfGroupOffer > 0) {
                        remainingQuantity = skusMap[sku] ?: 0
                    }
                }
                // Add the price of remaining items that do not qualify for any offer
                totalPrice += remainingQuantity * item.price
            } else {
                // Invalid SKU found
                return -1
            }
        }
        return totalPrice
    }

    /**
     * Handle group offers by adjusting the skusMap to account for group discounts
     * @param skusMap The map containing the SKUs and their quantities
     * @param sku The SKU being processed
     * @param offer The offer being processed
     * @param totalPrice The total price being calculated
     * @param processedForGroupOffers The map to track which SKUs have been processed for group offers
     * @return The total price after applying group offers
     */
    fun handleGroupOffers(skusMap: MutableMap<String, Int>, sku: String, offer: OfferType): Int {
        var totalPrice = 0
        val groupOffer = offer.offerDetail as? OfferType.OfferDetail.GroupOffer ?: return 0

        val groupSKUs = groupOffer.groupSKUs
        val groupQuantity = groupOffer.groupQuantity
        val offerPrice = groupOffer.offerPrice

        // Calculate total items in the group
        var totalGroupItems = 0
        for (groupSKU in groupSKUs) {
            totalGroupItems += skusMap[groupSKU] ?: 0
        }

        println("Total group items for SKUs $groupSKUs: $totalGroupItems")

        // Calculate how many times the group offer can be applied
        val numberOfGroupOffers = totalGroupItems / groupQuantity

        println("Total group items for $groupSKUs: $totalGroupItems")

        if (numberOfGroupOffers > 0) {
            totalPrice += numberOfGroupOffers * offerPrice

            // Deduct the used items from the skusMap
            var itemsToDeduct = numberOfGroupOffers * groupQuantity
            val sortedGroupSKUs = groupSKUs.sortedByDescending { ItemRepository.getItem(it)?.price ?: 0 }
            for (groupSKU in sortedGroupSKUs) { // Should be in order of most expensive to the least expensive
                val availableItems = skusMap[groupSKU] ?: 0
                if (availableItems > 0) {
                    val deductCount = minOf(availableItems, itemsToDeduct)
                    skusMap[groupSKU] = availableItems - deductCount
                    itemsToDeduct -= deductCount
                    if (itemsToDeduct == 0) break
                }
            }
        }
        println("new skusMap after group offer deduction: $skusMap")
        return totalPrice
    }

    /**
     * Handle free item offers by adjusting the skusMap to account for free items
     * @param skusMap The map containing the SKUs and their quantities
     */
    fun handleFreeItemOffers(skusMap: MutableMap<String, Int>) {
        for ((sku, quantity) in skusMap) {
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                var remainingQuantity = quantity
                val sortedOffers = item.specialOffers?.sortedByDescending { it.requiredQuantity } ?: emptyList()
                for (offer in sortedOffers) {
                    // Change the order of processing: only process FreeItemOffer here
                    when (offer.offerDetail) {
                        is OfferType.OfferDetail.FreeItemOffer -> {
                            // Do nothing, continue to while loop below
                        }
                        else -> {
                            // Skip price and group offers in this pass
                            continue
                        }
                    }
                    while (remainingQuantity >= offer.requiredQuantity) {
                         if (sku == offer.offerDetail.freeItemSKU) { // Special case: free item is the same as the purchased item (e.g., F)
                             if (remainingQuantity < offer.requiredQuantity + offer.offerDetail.freeItemQuantity) {
                                 // Not enough items to qualify for free items
                                 break
                             }
                         }
                         val freeItemSKU = offer.offerDetail.freeItemSKU
                         val freeItemQuantity = offer.offerDetail.freeItemQuantity
                         val toDeductIfSameSku = deductFreeItemsQuantity(sku, skusMap, freeItemSKU, freeItemQuantity)

                         if (sku == offer.offerDetail.freeItemSKU) {
                             // Adjust remaining quantity for same SKU free item offers
                             remainingQuantity -= toDeductIfSameSku
                         }
                         remainingQuantity -= offer.requiredQuantity
                    }
                }
            }
        }
    }

    /***
     * Deduct the price of free items from the total price
     * @param skus The string containing the SKUs of all the products in the basket
     * @param freeItemSKU The SKU of the free item
     * @param freeItemQuantity The quantity of the free item
     * @return The total price to deduct for the free items
     */
    fun deductFreeItemsQuantity(sku: String, skusMap: MutableMap<String, Int>, freeItemSKU: String, freeItemQuantity: Int): Int {
        val freeItemCountInSkus = skusMap[freeItemSKU] ?: 0
        if (freeItemCountInSkus >= freeItemQuantity) {
            val freeItem = ItemRepository.getItem(freeItemSKU)
            if (freeItem != null) {
                // Decrement the free items from the count in skus
                //println("Deducting $freeItemQuantity of free item $skusMap from SKU $sku")
                skusMap[freeItemSKU] = (skusMap[freeItemSKU] ?: 0) - (freeItemQuantity)
                return freeItemQuantity
            }
        }
        return 0
    }
}



