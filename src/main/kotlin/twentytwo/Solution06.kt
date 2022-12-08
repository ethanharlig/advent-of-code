package twentytwo

class Solution06 {
    fun main() {
        val myTest = doStuff("/src/main/resources/twentytwo/06/test.txt")
        println(myTest)

        val myActual = doStuff("/src/main/resources/twentytwo/06/actual.txt")
        println(myActual)
    }

    private fun doStuff(filePath: String): Int {
        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            findStartOfMessage(it)
        }
        return 0
    }

    private fun findStartOfMessage(line: String): Int {
        var curStr = ""
        for (ndx in line.indices) {
            if (curStr.length == 14) {
                curStr = curStr.substring(1)
            }
            curStr += line[ndx]

            if (curStr.toCharArray().distinct().size == 14) {
                println("Found character: ${ndx + 1}")
                return ndx + 1
            }
        }
        return -1
    }
}
