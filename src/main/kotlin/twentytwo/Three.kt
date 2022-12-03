package twentytwo

import java.lang.RuntimeException

class Three {

    fun main() {
        val myTestPriorities = doStuff("/src/main/resources/twentytwo/three/test.txt")
        println(myTestPriorities)

        val actualPriorities = doStuff("/src/main/resources/twentytwo/three/actual.txt")
        println(actualPriorities)
    }

    private fun doStuff(filePath: String): Int {
        var priorityScores = 0

        val elfGroup = mutableListOf<String>()
        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            // part 1
            // val pairStr = getTwoStrings(it)
            // cur is left off at the first element of the second substr
            // val commonType = findCommonType(pairStr.first, pairStr.second)

            // part 2
            elfGroup += it
            if (elfGroup.size == 3) {
                val commonType = findCommonType(elfGroup[0], elfGroup[1], elfGroup[2])
                val score = getPriorityValue(commonType)
                priorityScores += score
                elfGroup.clear()
            }
        }
        return priorityScores
    }

    fun getTwoStrings(str: String): Pair<String, String> {
        var cur = 0
        var future = 0
        var curStr = ""

        while (future < str.length) {
            cur++
            future += 2
            curStr = str.substring(0, cur)
        }
        return Pair(curStr, str.substring(cur, str.length))
    }

    fun findCommonType(first: String, second: String): Char {
        val intersection = first.toSet().intersect(second.toSet())
        if (intersection.size != 1) {
            throw RuntimeException("Didn't find one character for these two: $first; $second")
        }

        return intersection.first()
    }

    private fun findCommonType(first: String, second: String, third: String): Char {
        val intersection = first.toSet().intersect(second.toSet()).intersect(third.toSet())
        if (intersection.size != 1) {
            throw RuntimeException("Didn't find one character for these two: $first; $second")
        }

        return intersection.first()
    }

    fun getPriorityValue(strVal: Char): Int {
        if (strVal.isLowerCase()) {
            return strVal.minus('a').plus(1)
        }
        return strVal.minus('A').plus(27)
    }
}