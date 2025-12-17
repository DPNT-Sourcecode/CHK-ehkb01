package solutions.CHK

import kotlin.collections.mutableMapOf


/*** *
 * checkout(string) -> integer
 *  - param[0] = a string containing the SKUs of all the products in the basket
 *  - @return = an integer representing the total checkout value of the items
 *
 *  NOTE: Offers involving multiple items always give a better discount than offers containing fewer items.
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

        skusMap.forEach { (sku, quantity) ->
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                //Always check the biggest offer first, if quantity is less or has remaining items, check the next offer
                var remainingQuantity = quantity
                val sortedOffers = item.specialOffers?.sortedByDescending { it.requiredQuantity } ?: emptyList()
                for (offer in sortedOffers) {
                    while (remainingQuantity >= offer.requiredQuantity) {
                        when (offer.offerDetail) {
                            is OfferType.OfferDetail.PriceOffer -> {
                                totalPrice += offer.offerDetail.offerPrice
                            }

                            is OfferType.OfferDetail.FreeItemOffer -> {
                                // Free item offer does not affect the price of the current item
                                // But the price of the item required for the free item should be calculated
                                totalPrice += item.price * offer.requiredQuantity


                                // But what if the free item is the same as the current item?
                                // Or if the items are not ordered? and it already counted?
                                val freeItemSKU = offer.offerDetail.freeItemSKU
                                val freeItemQuantity = offer.offerDetail.freeItemQuantity
                                totalPrice -= deductFreeItemsPrice(skusMap, freeItemSKU, freeItemQuantity)
                                if ((skusMap[freeItemSKU] ?: 0) > freeItemQuantity) {
                                    skusMap[freeItemSKU] = (skusMap[freeItemSKU] ?: 0) - freeItemQuantity
                                }
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
    fun deductFreeItemsPrice(skusMap: Map<String, Int>, freeItemSKU: String, freeItemQuantity: Int): Int {
        val freeItemCountInSkus = skusMap[freeItemSKU] ?: 0
        if (freeItemCountInSkus > freeItemQuantity) {
            val freeItem = ItemRepository.getItem(freeItemSKU)
            if (freeItem != null) {
                // Decrement the free items from the count in skus
                skusMap[freeItemSKU]?.minus(freeItemQuantity)
                return freeItem.price * freeItemCountInSkus
            }
        }
        return 0
    }
}

