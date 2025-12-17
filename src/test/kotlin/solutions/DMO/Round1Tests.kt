package solutions.DMO

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Round1Tests {
    @Test
    @DisplayName("sum test with 2 correct numbers")
    fun sum() {
        val result = DemoRound1Solution().sum(10, 20)
        Assertions.assertEquals(30, result)
    }

    @Test
    @DisplayName("sum test with one number equal to 0")
    fun sumWithZero() {
        val result = DemoRound1Solution().sum(0, 15)
        Assertions.assertEquals(15, result)
    }

    @Test
    @DisplayName("sum test with one number equal to 100")
    fun sumWithOneHundred() {
        val result = DemoRound1Solution().sum(20, 100)
        Assertions.assertEquals(120, result)
    }

    @Test
    @DisplayName("sum test with one number out of bounds (negative)")
    fun sumWithNegativeNumber() {
        val result = DemoRound1Solution().sum(20, -1)
        Assertions.assertEquals(120, result)
    }

}