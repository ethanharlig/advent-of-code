package twentytwo

import java.util.*

class Five {
    fun main() {
//        val myTest = doStuff("/src/main/resources/twentytwo/five/test.txt") println(myTest)

        val myActual = doStuff("/src/main/resources/twentytwo/five/actual.txt")
        println(myActual)
    }

    var stacks = mutableListOf<Deque<String>>()
    private fun doStuff(filePath: String): String {
        // get stacks indexed
        // for each operation doMovement(numTimes, fromStack, toStack)
        // for doMovement, (pop from fromStack and push onto toStack) * numTimes

        var processingStacks = true
        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            if (it.trim().startsWith("1")) {
                processingStacks = false
            }

            if (processingStacks) {
                addToStacksFromLine(it)
            }

            if (it.startsWith("move")) {
                val lineCommands = it.substringAfter("move ").trim().split(" ")
                // doStackMovementOneByOne(lineCommands[0].toInt(), lineCommands[2].toInt() - 1, lineCommands[4].toInt() - 1)
                doStackMovementManyAtOnce(lineCommands[0].toInt(), lineCommands[2].toInt() - 1, lineCommands[4].toInt() - 1)
            }
        }

        return stacks.joinToString("") { if (it.isNotEmpty()) it.first().toString() else "" }
    }

    // part 1
    private fun doStackMovementOneByOne(numTimes: Int, fromStack: Int, toStack: Int) {
        println("\n\ndo one by one movement: $numTimes from $fromStack to $toStack")
        for (ndx in 0 until numTimes) {
            println("BEFORE ndx: $ndx, stacks: $stacks")
            val toMove = stacks[fromStack].pop()
            stacks[toStack].push(toMove)
            println("AFTER ndx: $ndx, stacks: $stacks")
        }
    }

    // part 2
    private fun doStackMovementManyAtOnce(numTimes: Int, fromStack: Int, toStack: Int) {
        println("\n\ndo many movement: $numTimes from $fromStack to $toStack")

        val allToMove = mutableListOf<String>()
        for (ndx in 0 until numTimes) {
            allToMove.add(0, stacks[fromStack].pop())
        }
        for (toMove in allToMove) {
            stacks[toStack].push(toMove)
        }
    }

    private fun addToStacksFromLine(line: String) {
        for (ndx in 0..(line.length - 3) step 4) {
            if (stacks.size <= ndx / 4) {
                stacks.add(ArrayDeque())
            }
            val thisStack = line.substring(ndx, ndx + 3).trim()

            // skip empty entry for this stack
            if (thisStack.isEmpty() || thisStack.all { it.isWhitespace() }) continue

            val crateName = thisStack.substringAfter("[").substringBefore("]")
            // add it to the end because we're building from top of stack downwards
            stacks[ndx / 4].addLast(crateName)
        }
    }
}
