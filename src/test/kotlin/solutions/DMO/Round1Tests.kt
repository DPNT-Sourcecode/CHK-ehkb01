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
    @DisplayName("sum test with number out of bounds (negative)")
    fun sumWithNegativeNumber() {
        var throwable: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(20, -1)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)

        throwable = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(-1, 0)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)

        throwable = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(-1, -1)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)
    }

    @Test
    @DisplayName("sum test with number out of bounds (positive)")
    fun sumWithPositiveNumber() {
        var throwable: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(20, 101)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)

        throwable = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(101, 0)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)

        throwable = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(103, 104)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)
    }

    @Test
    @DisplayName("sum test with numbers out of bounds (positive and negative)")
    fun sumWithPositiveAndNegativeNumber() {
        var throwable: IllegalArgumentException = Assertions.assertThrows(IllegalArgumentException::class.java) {
            DemoRound1Solution().sum(-1, 101)
        }
        Assertions.assertEquals(IllegalArgumentException::class.java, throwable.javaClass)

    }

}