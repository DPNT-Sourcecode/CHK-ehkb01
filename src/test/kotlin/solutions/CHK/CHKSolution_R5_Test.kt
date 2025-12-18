package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R5_Test {
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
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with more than 3 items")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithMoreThan3Items() {
        val result = CheckoutSolution().checkout("STXYZ")
        Assertions.assertEquals(90, result)
    }

    @Test
    @DisplayName("checkout test with group offer of 3 items S,T,X,Y,Z with multiple offers")
    fun checkoutWithGroupOfferOf3ItemsSTXYZWithMultipleOffers() {
        val result = CheckoutSolution().checkout("SSSTTTXXXYYYZZZ")
        Assertions.assertEquals(225, result)
    }
}