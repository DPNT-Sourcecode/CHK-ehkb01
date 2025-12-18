package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R4_Test {
    @Test
    @DisplayName("chechout test with free item offers R and Q")
    fun checkoutWithFreeItemOffersRAndQ() {
        val result = CheckoutSolution().checkout("RRRQ")
        Assertions.assertEquals(150, result)
    }

    @Test
    @DisplayName("chechout test with free item offers R and multiple Qs")
    fun checkoutWithFreeItemOffersRAndMultipleQs() {
        val result = CheckoutSolution().checkout("RRRQQ")
        Assertions.assertEquals(180, result)
    }

    @Test
    @DisplayName("chechout test with free item offers R and multiple Rs and Qs")
    fun checkoutWithFreeItemOffersRAndMultipleRsAndQs() {
        val result = CheckoutSolution().checkout("RRRRQQ")
        Assertions.assertEquals(230, result)
    }

    @Test
    @DisplayName("chechout test with free item offers R and multiple and Qs that should not get the Q special offer")
    fun checkoutWithFreeItemOffersRAndMultipleAndQsThatShouldNotGetTheQSpecialOffer() {
        val result = CheckoutSolution().checkout("RRRQQQ")
        Assertions.assertEquals(230, result)
    }

    @Test
    @DisplayName("chechout test with free item offers for U")
    fun checkoutWithFreeItemOffersForU() {
        val result = CheckoutSolution().checkout("UUUU")
        Assertions.assertEquals(120, result)
    }

    @Test
    @DisplayName("chechout test with not enough for free item offers for U")
    fun checkoutWithNotEnoughForFreeItemOffersForU() {
        val result = CheckoutSolution().checkout("UUU")
        Assertions.assertEquals(120, result)
    }
}
