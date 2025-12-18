package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R5_Test {
    @Test
    @DisplayName("checkout test with not enough for group offer of 3 items S,T,X,Y,Z")
    fun checkoutWithNotEnoughForGroupOffer() {
        val result = CheckoutSolution().checkout("ST")
        Assertions.assertEquals(40, result)
    }
    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z")
    fun checkoutWithGroupOfferOf3ItemsSTXYZ() {
        val result = CheckoutSolution().checkout("STX")
        Assertions.assertEquals(45, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with different items")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithDifferentItems() {
        val result = CheckoutSolution().checkout("SYZ")
        Assertions.assertEquals(45, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with more than 3 items. Should apply offer for 3 items with biggest prices.")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithMoreThan3Items() {
        val result = CheckoutSolution().checkout("STXYZ")
        Assertions.assertEquals(45 + 20 + 17, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X with multiple offers")
    fun checkoutWithGroupOfferOf3ItemsSTXWithMultipleOffers() {
        val result = CheckoutSolution().checkout("STXSTX")
        Assertions.assertEquals(90, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with multiple offers")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithMultipleOffers() {
        val result = CheckoutSolution().checkout("SSSTTTXXXYYYZZZ")
        Assertions.assertEquals(225, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with multiple offers and remaining items")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithMultipleOffersAndRemainingItems() {
        val result = CheckoutSolution().checkout("SSSTTTXXXYYYZZZY")
        Assertions.assertEquals(225 + 17, result)
    }
}