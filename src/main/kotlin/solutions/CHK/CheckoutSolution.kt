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
        for ((sku, quantity) in skusMap) {
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                var remainingQuantity = quantity
                val sortedOffers = item.specialOffers?.sortedByDescending { it.requiredQuantity } ?: emptyList()
                for (offer in sortedOffers) {
                    while (remainingQuantity >= offer.requiredQuantity) {
                        when (offer.offerDetail) {
                            is OfferType.OfferDetail.FreeItemOffer -> {

                                if (sku == offer.offerDetail.freeItemSKU) { // Special case: free item is the same as the purchased item (e.g., F)
                                    if (remainingQuantity < offer.requiredQuantity + offer.offerDetail.freeItemQuantity) {
                                        // Not enough items to qualify for free items
                                        break
                                    }
                                    //remainingQuantity -= offer.requiredQuantity
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

                            else -> {
                                // Do nothing for price offers in the first pass
                            }
                        }
                    }
                }
            }
        }

        // 2nd pass to calculate total price
        for ((sku, quantity) in skusMap) {
            println("$sku - $quantity")
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                //Always check the biggest offer first, if quantity is less or has remaining items, check the next offer
                var remainingQuantity = quantity
                val sortedOffers = item.specialOffers?.sortedByDescending { it.requiredQuantity } ?: emptyList()
                for (offer in sortedOffers) {
                    while (remainingQuantity >= offer.requiredQuantity) {
                        println("remainingQuantity: $remainingQuantity for SKU: $sku applying offer: $offer")
                        when (offer.offerDetail) {
                            is OfferType.OfferDetail.PriceOffer -> {
                                totalPrice += offer.offerDetail.offerPrice
                            }

                            is OfferType.OfferDetail.FreeItemOffer -> {
                                totalPrice += item.price * offer.requiredQuantity
                            }
                        }
                        remainingQuantity -= offer.requiredQuantity
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
