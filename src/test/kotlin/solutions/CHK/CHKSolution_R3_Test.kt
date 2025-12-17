package solutions.CHK

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CHKSolution_R3_Test {
    @Test
    @DisplayName("chechout test with free item offers F")
    fun checkoutWithFreeItemOffersEAndMultipleBsAndRemainingB() {
        val result = CheckoutSolution().checkout("FFF")
        Assertions.assertEquals(20, result)
    }

    @Test
    @DisplayName("chechout test with free item offers F and more Fs")
    fun checkoutWithFreeItemOffersFAndMoreFs() {
        val result = CheckoutSolution().checkout("FFFFFF")
        Assertions.assertEquals(40, result)
    }

    @Test
    @DisplayName("chechout test with free item offers F and odd number of Fs")
    fun checkoutWithFreeItemOffersFAndOddNumberOfFs() {
        val result = CheckoutSolution().checkout("FFFFFFF")
        Assertions.assertEquals(50, result)
    }

    @Test
    @DisplayName("chechout test with free item offers F and even number of Fs")
    fun checkoutWithFreeItemOffersFAndEvenNumberOfFs() {
        val result = CheckoutSolution().checkout("FFFF")
        Assertions.assertEquals(30, result)
    }

    @Test
    @DisplayName("chechout test with two Fs not qualifying for free item")
    fun checkoutWithTwoFsNotQualifyingForFreeItem() {
        val result = CheckoutSolution().checkout("FF")
        Assertions.assertEquals(20, result)
    }
}