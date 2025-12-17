package solutions.DMO

import java.util.Locale.getDefault

class DemoRound1Solution {
    fun sum(num1: Int, num2: Int): Int {
        if (num1 !in 0..100) { //(num1 !in 1..<100)
            throw IllegalArgumentException("num1 is out of bounds")
        }
        if (num2 !in 0..100) {
            throw IllegalArgumentException("num2 is out of bounds")
        }
        return num1 + num2
    }

    fun increment(x: Int): Int {
        return x+1
    }

    fun toUppercase(text: String): String {
        return text.uppercase(getDefault())
    }

    fun letterToSanta(): String {
        return "Dear Santa, I have been very good this year. Please get me hired. Thank you!"
    }

    fun countLines(text: String): Int {
        return text.split('\n').size
    }
}

