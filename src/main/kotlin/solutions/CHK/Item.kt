package solutions.CHK

data class Item(
    val name: String,
    val price: Int,
    val quantity: Int,
    val specialOffer: SpecialOffer? = null
)

data class SpecialOffer(
    val requiredQuantity: Int,
    val offerPrice: Int
)

object ItemRepository {
    private val items = listOf(
        Item("A", 50, 0, SpecialOffer(3, 130)),
        Item("B", 30, 0, SpecialOffer(2, 45)),
        Item("C", 20, 0),
        Item("D", 15, 0)
    )

    fun getItem(sku: String): Item? {
        return items.find { it.name == sku }
    }
}

