package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R1_Test {
    @Test
    @DisplayName("chechout test with just one item A")
    fun checkoutWithOneItemA() {
        val result = CheckoutSolution().checkout("A")
        Assertions.assertEquals(50, result)
    }

    @Test
    @DisplayName("chechout test with different items")
    fun checkoutWithDifferentItems() {
        val result = CheckoutSolution().checkout("ABCD")
        Assertions.assertEquals(115, result)
    }

    @Test
    @DisplayName("chechout test with special offer for item A")
    fun checkoutWithSpecialOfferForItemA() {
        val result = CheckoutSolution().checkout("AAA")
        Assertions.assertEquals(130, result)
    }

}