package twentytwo

import FileUtil

class One {
    fun main() {
        val basicTest = readCalculateSortCalories("/src/main/resources/twentytwo/one/test.txt")
        println("basic test most: ${basicTest[0].sum()}")

        val caloriesInOrder = readCalculateSortCalories("/src/main/resources/twentytwo/one/actual.txt")
        // part 1
        println("actual data most: ${caloriesInOrder[0].sum()}")
        // part 2
        println("top three together: ${caloriesInOrder.subList(0, 3).sumOf { it.sum() }}")
    }

    fun readCalculateSortCalories(filePath: String): List<List<Int>> {
        val allElves = ArrayList<List<Int>>()

        var curElfCalories = ArrayList<Int>()
        var currentlyProcessingElf = false
        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            val trimmedValue = it.trim()
            if (trimmedValue == "") {
                allElves.add(curElfCalories)
                curElfCalories = ArrayList()
                currentlyProcessingElf = false
            } else {
                curElfCalories.add(trimmedValue.toInt())
                currentlyProcessingElf = true
            }
        }
        // in case we didn't add the last elf
        if (currentlyProcessingElf) {
            allElves.add(curElfCalories)
        }

        allElves.sortByDescending { it.sum() }
        return allElves
    }
}
