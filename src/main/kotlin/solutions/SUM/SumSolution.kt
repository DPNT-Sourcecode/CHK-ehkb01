package solutions.SUM

class SumSolution {
    fun sum(x: Int, y: Int): Int {
        if (x !in 0..100) { //(num1 !in 1..<100)
            throw IllegalArgumentException("num1 is out of bounds")
        }
        if (y !in 0..100) {
            throw IllegalArgumentException("num2 is out of bounds")
        }
        return x + y
    }
}
