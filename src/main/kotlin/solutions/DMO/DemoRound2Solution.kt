package solutions.DMO

class DemoRound2Solution {
    fun arraySum(integerList: List<Int>): Int {
        return integerList.sum()
    }

    fun intRange(start: Int, end: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (j in start..end) {
            result.add(j)
        }
        return result
    }

    fun filterPass(integerList: List<Int>, threshold: Int): List<Int> {
        return integerList.filter { it >= threshold }
    }
}
