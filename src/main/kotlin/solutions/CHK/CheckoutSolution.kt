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
                val specialOffer = item.specialOffers?.find {
                    when (it.offerDetail) {
                        is OfferType.OfferDetail.PriceOffer -> true
                        else -> false
                    }
                }?.let {
                    when (it.offerDetail) {
                        is OfferType.OfferDetail.PriceOffer -> it
                        else -> null
                    }
                }
                if (specialOffer != null) {
                    val offerQuantity = specialOffer.requiredQuantity
                    val offerPrice = (specialOffer.offerDetail as OfferType.OfferDetail.PriceOffer).offerPrice
                    val numberOfOffers = quantity / offerQuantity
                    val remainingItems = quantity % offerQuantity
                    totalPrice += numberOfOffers * offerPrice + remainingItems * item.price
                } else {
                    totalPrice += quantity * item.price
                }
            } else {
                // Invalid SKU found
                return -1
            }
        }
        return totalPrice
    }
}