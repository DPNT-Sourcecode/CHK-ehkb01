package solutions.CHK


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
        skus.split("").filter { it.isNotEmpty() }.groupingBy { it }.eachCount().forEach { (sku, quantity) ->
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
                            }
                        }
                        remainingQuantity -= offer.requiredQuantity
                    }
                }
            } else {
                // Invalid SKU found
                return -1
            }
        }
        return totalPrice
    }
}