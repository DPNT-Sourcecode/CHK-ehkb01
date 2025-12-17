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
}