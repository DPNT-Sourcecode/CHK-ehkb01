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

