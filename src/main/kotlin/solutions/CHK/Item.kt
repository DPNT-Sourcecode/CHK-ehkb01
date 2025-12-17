package solutions.CHK

data class Item(
    val name: String,
    val price: Int,
    val quantity: Int,
    val specialOffers: List<OfferType>? = null
)

// Offer can be of the form "3A for 130" or "2E get one B free"
data class OfferType(
    val requiredQuantity: Int,
    val offerDetail: OfferDetail
) {
    sealed class OfferDetail {
        data class PriceOffer(val offerPrice: Int) : OfferDetail()
        data class FreeItemOffer(val freeItemSKU: String, val freeItemQuantity: Int) : OfferDetail()
    }
}

object ItemRepository {
    private val items = listOf(
        Item("A", 50, 0, listOf(OfferType(3, OfferType.OfferDetail.PriceOffer(130)), OfferType(5, OfferType.OfferDetail.PriceOffer(200)))),
        Item("B", 30, 0, listOf(OfferType(2, OfferType.OfferDetail.PriceOffer(45)))),
        Item("C", 20, 0),
        Item("D", 15, 0),
        Item("E", 40, 0, listOf(OfferType(2, OfferType.OfferDetail.FreeItemOffer("B", 1)))),
        Item("F", 10, 0, listOf(OfferType(2, OfferType.OfferDetail.FreeItemOffer("F", 1))))
    )

    fun getItem(sku: String): Item? {
        return items.find { it.name == sku }
    }
}
