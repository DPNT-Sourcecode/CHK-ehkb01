package solutions.CHK


/*** *
 * checkout(string) -> integer
 *  - param[0] = a string containing the SKUs of all the products in the basket
 *  - @return = an integer representing the total checkout value of the items
 */
class CheckoutSolution {
    fun checkout(skus: String): Int {
        //calculate total price of items in the skus string
        var totalPrice = 0
        skus.split("").filter { it.isNotEmpty() }.groupingBy { it }.eachCount().forEach { (sku, quantity) ->
            val item = ItemRepository.getItem(sku)
            if (item != null) {
                val specialOffer = item.specialOffer
                if (specialOffer != null) {
                    val offerQuantity = quantity / specialOffer.requiredQuantity
                    val remainingQuantity = quantity % specialOffer.requiredQuantity
                    totalPrice += offerQuantity * specialOffer.offerPrice + remainingQuantity * item.price
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