package twentytwo

import java.lang.RuntimeException

class Three {

    fun main() {
        val myTestPriorities = doStuff("/src/main/resources/twentytwo/three/test.txt")
        println(myTestPriorities)
    }

    fun doStuff(filePath: String): Int {
        var priorityScores = 0

        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            var cur = 0
            var future = 0
            var curStr = ""

            while (future < it.length) {
                cur++
                future += 2
                curStr = it.substring(cur)
            }

            // cur is left off at the first element of the second substr
            val commonType = findCommonType(curStr, it.substring(cur, it.length))
            val score = getPriorityValue(commonType)
            priorityScores += score
        }
        return priorityScores
    }

    fun findCommonType(first: String, second: String): Char {
        val intersection = first.toSet().intersect(second.toSet())
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