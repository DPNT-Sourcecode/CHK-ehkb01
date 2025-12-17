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

    @Test
    @DisplayName("chechout test with more than 1 special offer")
    fun checkoutWithMoreThanOneSpecialOffer() {
        val result = CheckoutSolution().checkout("AAABB")
        Assertions.assertEquals(175, result)
    }

    @Test
    @DisplayName("chechout test with more than 1 special offer and remaining items for item A")
    fun checkoutWithMoreThanOneSpecialOfferAndRemainingItems() {
        val result = CheckoutSolution().checkout("AAAA")
        Assertions.assertEquals(180, result)
    }

    @Test
    @DisplayName("chechout test with invalid SKU")
    fun checkoutWithInvalidSKU() {
        val result = CheckoutSolution().checkout("ABCDX")
        Assertions.assertEquals(-1, result)
    }

    @Test
    @DisplayName("chechout test with empty string")
    fun checkoutWithEmptyString() {
        val result = CheckoutSolution().checkout("")
        Assertions.assertEquals(0, result)
    }

    @Test
    @DisplayName("chechout test with multiple special offers and remaining items")
    fun checkoutWithMultipleSpecialOffersAndRemainingItems() {
        val result = CheckoutSolution().checkout("AAAAAABBBCCD")
        Assertions.assertEquals(390, result)
    }
}