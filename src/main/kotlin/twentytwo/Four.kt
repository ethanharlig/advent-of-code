package twentytwo

class Four {
    fun main() {
        val myTest = doStuff("/src/main/resources/twentytwo/four/test.txt")
        println(myTest)

        val myActual = doStuff("/src/main/resources/twentytwo/four/actual.txt")
        println(myActual)
    }

    private fun doStuff(filePath: String): Int {
        var overlappingCount = 0

        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            val twoVals = it.split(",")
            val firstList = getAssignmentListFromString(twoVals[0])
            val secondList = getAssignmentListFromString(twoVals[1])

            // part 1
            // if (firstList.containsAll(secondList) || secondList.containsAll(firstList)) {
                // overlappingCount++
            // }

            // part 2
            if (firstList.first() >= secondList.first() && firstList.first() <= secondList.last()) {
                overlappingCount++
            } else if (secondList.first() >= firstList.first() && secondList.first() <= firstList.last()) {
                overlappingCount++
            }
        }
        return overlappingCount
    }

    private fun getAssignmentListFromString(assignmentString: String): List<Int> {
        val nums = assignmentString.split("-")
        val toRet = mutableListOf<Int>()
        for (ndx in nums[0].toInt()..nums[1].toInt()) {
            toRet.add(ndx)
        }
        return toRet
    }
}