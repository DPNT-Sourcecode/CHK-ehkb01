package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R2_Test {
    @Test
    @DisplayName("chechout test with 5 items A")
    fun checkoutWithBiggestDiscountItemA() {
        val result = CheckoutSolution().checkout("AAAAA")
        Assertions.assertEquals(200, result)
    }

    @Test
    @DisplayName("chechout test with free item offer E")
    fun checkoutWithFreeItemOfferE() {
        val result = CheckoutSolution().checkout("EE")
        Assertions.assertEquals(80, result)
    }

    @Test
    @DisplayName("chechout test with free item offer E and B")
    fun checkoutWithFreeItemOfferEAndB() {
        val result = CheckoutSolution().checkout("EEB")
        Assertions.assertEquals(80, result)
    }

    @Test
    @DisplayName("chechout test when 2 different offers can be applied for the same SKU")
    fun checkoutWhenTwoDifferentOffersCanBeApplied() {
        val result = CheckoutSolution().checkout("AAAAAAAA")
        Assertions.assertEquals(330, result)
    }

    @Test
    @DisplayName("chechout test when 2 different offers can be applied for the same SKU and remaining items")
    fun checkoutWhenTwoDifferentOffersCanBeAppliedAndRemaining() {
        val result = CheckoutSolution().checkout("AAAAAAAAA")
        Assertions.assertEquals(380, result)
    }

    @Test
    @DisplayName("chechout test with free item offers E and remaining")
    fun checkoutWithFreeItemOffersEAndRemaining() {
        val result = CheckoutSolution().checkout("EEEB")
        Assertions.assertEquals(120, result)
    }

    @Test
    @DisplayName("chechout test with free item offers E and multiple Bs")
    fun checkoutWithFreeItemOffersEAndMultipleBs() {
        val result = CheckoutSolution().checkout("EEEEBB")
        Assertions.assertEquals(160, result)
    }

    @Test
    @DisplayName("chechout test with free item offers E and multiple Bs and remaining B")
    fun checkoutWithFreeItemOffersEAndMultipleBsAndRemainingB() {
        val result = CheckoutSolution().checkout("EEEEBBB")
        Assertions.assertEquals(80, result)
    }

}