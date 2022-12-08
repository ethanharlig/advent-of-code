package twentytwo

import FileUtil
import kotlin.math.abs

class Solution08 {

    fun main() {
        val myTest = doStuff("/src/main/resources/twentytwo/08/test.txt")
        println(myTest)

        val myActual = doStuff("/src/main/resources/twentytwo/08/actual.txt")
        println(myActual)
    }

    private fun doStuff(filePath: String): Int {
        // read line by line, build map -- 2d grid -- List<List<Int>>
        // for each tree (Int), check isVisible()

        val curMap = mutableListOf<List<Int>>()
        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            val curRow = mutableListOf<Int>()
            it.forEach { char -> curRow.add(char.digitToInt()) }
            curMap += curRow
        }

        var numVisible = 0
        var bestViewingScore = 0
        println("curMap: $curMap")
        curMap.forEachIndexed { x, row ->
            row.forEachIndexed { y, height ->
                val up = Pair(x + 1, y)
                val down = Pair(x - 1, y)
                val left = Pair(x, y - 1)
                val right = Pair(x, y + 1)

                // part 1
//                if (isVisible(curMap, Pair(x, y), listOf(up, down, left, right), height, Pair(x, y))) {
//                    numVisible++
//                }
                var totalViewingScore = 1
                listOf(up, down, left, right).forEach() {
                    val viewingDistance = getViewingDistanceInDirection(
                        curMap,
                        Pair(x, y),
                        it,
                        height,
                        Pair(x, y)
                    )
                    if (viewingDistance != 0) {
                        totalViewingScore *= viewingDistance
                    }
                }
                if (totalViewingScore > bestViewingScore) bestViewingScore = totalViewingScore
            }
        }
        return bestViewingScore
    }

    fun isVisible(
        map: List<List<Int>>,
        curLocation: Pair<Int, Int>,
        possibleDirections: List<Pair<Int, Int>>,
        originalHeight: Int,
        originalPosition: Pair<Int, Int>
    ): Boolean {
        val x = curLocation.first
        val y = curLocation.second

        // reached edge row
        if (x == 0 || x == map.size - 1) {
            return true
        }
        // reached edge col
        if (y == 0 || y == map[0].size - 1) {
            return true
        }

        return possibleDirections.map { direction ->
            var isVisibleInThisDirection = false
            val heightThisDirection = map[direction.first][direction.second]
            // if we're taller, keep going in this direction
            if (originalHeight > heightThisDirection) {
                val newDirection =
                    if (direction.first != x) {
                        if (direction.first > x) {
                            Pair(direction.first + 1, direction.second)
                        } else {
                            Pair(direction.first - 1, direction.second)
                        }
                    } else {
                        assert(direction.second != y)

                        if (direction.second > y) {
                            Pair(direction.first, direction.second + 1)
                        } else {
                            Pair(direction.first, direction.second - 1)
                        }
                    }
                isVisibleInThisDirection =
                    isVisible(map, direction, listOf(newDirection), originalHeight, originalPosition)
            }
            isVisibleInThisDirection
        }.any { it }
    }

    fun getViewingDistanceInDirection(
        map: List<List<Int>>,
        curLocation: Pair<Int, Int>,
        direction: Pair<Int, Int>,
        originalHeight: Int,
        originalPosition: Pair<Int, Int>
    ): Int {
        val x = curLocation.first
        val y = curLocation.second

        // reached edge row
        if (x == 0 || x == map.size - 1) {
            return abs(originalPosition.first - x)
        }
        // reached edge col
        if (y == 0 || y == map[0].size - 1) {
            return abs(originalPosition.second - y)
        }

        val heightThisDirection = map[direction.first][direction.second]
        // if we're taller, keep going in this direction
        if (originalHeight > heightThisDirection) {
            val newDirection =
                if (direction.first != x) {
                    if (direction.first > x) {
                        Pair(direction.first + 1, direction.second)
                    } else {
                        Pair(direction.first - 1, direction.second)
                    }
                } else {
                    assert(direction.second != y)

                    if (direction.second > y) {
                        Pair(direction.first, direction.second + 1)
                    } else {
                        Pair(direction.first, direction.second - 1)
                    }
                }
            return getViewingDistanceInDirection(map, direction, newDirection, originalHeight, originalPosition)
        }

        return if (x != originalPosition.first) {
            abs(originalPosition.first - x) + 1
        } else {
            assert(y != originalPosition.second)
            abs(originalPosition.second - y) + 1
        }
    }
}
