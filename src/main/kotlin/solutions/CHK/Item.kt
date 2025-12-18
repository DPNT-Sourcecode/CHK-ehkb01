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
        data class GroupOffer(val groupSKUs: List<String>, val groupQuantity: Int, val offerPrice: Int) : OfferDetail()
    }
}

object ItemRepository {
    private val items = listOf(
        Item("A", 50, 0, listOf(OfferType(3, OfferType.OfferDetail.PriceOffer(130)), OfferType(5, OfferType.OfferDetail.PriceOffer(200)))),
        Item("B", 30, 0, listOf(OfferType(2, OfferType.OfferDetail.PriceOffer(45)))),
        Item("C", 20, 0),
        Item("D", 15, 0),
        Item("E", 40, 0, listOf(OfferType(2, OfferType.OfferDetail.FreeItemOffer("B", 1)))),
        Item("F", 10, 0, listOf(OfferType(2, OfferType.OfferDetail.FreeItemOffer("F", 1)))),
        Item("G", 20, 0),
        Item("H", 10, 0, listOf(OfferType(5, OfferType.OfferDetail.PriceOffer(45)), OfferType(10, OfferType.OfferDetail.PriceOffer(80)))),
        Item("I", 35, 0),
        Item("J", 60, 0),
        Item("K", 70, 0, listOf(OfferType(2, OfferType.OfferDetail.PriceOffer(120)))),
        Item("L", 90, 0),
        Item("M", 15, 0),
        Item("N", 40, 0, listOf(OfferType(3, OfferType.OfferDetail.FreeItemOffer("M", 1)))),
        Item("O", 10, 0),
        Item("P", 50, 0, listOf(OfferType(5, OfferType.OfferDetail.PriceOffer(200)))),
        Item("Q", 30, 0, listOf(OfferType(3, OfferType.OfferDetail.PriceOffer(80)))),
        Item("R", 50, 0, listOf(OfferType(3, OfferType.OfferDetail.FreeItemOffer("Q", 1)))),
        Item("S", 20, 0, listOf(OfferType(3, OfferType.OfferDetail.GroupOffer(listOf("S", "T", "X", "Y", "Z"), 3, 45)))),
        Item("T", 20, 0, listOf(OfferType(3, OfferType.OfferDetail.GroupOffer(listOf("S", "T", "X", "Y", "Z"), 3, 45)))),
        Item("U", 40, 0, listOf(OfferType(3, OfferType.OfferDetail.FreeItemOffer("U", 1)))),
        Item("V", 50, 0, listOf(OfferType(2, OfferType.OfferDetail.PriceOffer(90)), OfferType(3, OfferType.OfferDetail.PriceOffer(130)))),
        Item("W", 20, 0),
        Item("X", 17, 0, listOf(OfferType(3, OfferType.OfferDetail.GroupOffer(listOf("S", "T", "X", "Y", "Z"), 3, 45)))),
        Item("Y", 20, 0, listOf(OfferType(3, OfferType.OfferDetail.GroupOffer(listOf("S", "T", "X", "Y", "Z"), 3, 45)))),
        Item("Z", 21, 0, listOf(OfferType(3, OfferType.OfferDetail.GroupOffer(listOf("S", "T", "X", "Y", "Z"), 3, 45))))
    )

    fun getItem(sku: String): Item? {
        return items.find { it.name == sku }
    }
}
