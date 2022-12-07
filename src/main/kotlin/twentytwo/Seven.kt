package twentytwo

import FileUtil
import java.util.*

class Seven {

    private final val FILE_SYSTEM_SIZE = 70000000
    private final val UPDATE_SPACE_NEEDED = 30000000

    fun main() {
        val myTest = doStuff("/src/main/resources/twentytwo/seven/test.txt")
        println(myTest)
        val myActual = doStuff("/src/main/resources/twentytwo/seven/actual.txt")
        println(myActual)
    }

    private fun doStuff(filePath: String): Int {
        val fileSystem = mutableMapOf<String, MutableList<Pair<String, Int>>>()
        var currentPath = "/"

        fileSystem[currentPath] = mutableListOf()
        var readingForLsCommand = true
        var currentLsCommands = mutableListOf<String>()

        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            if (isCommand(it)) {
                if (readingForLsCommand) {
                    doLs(currentLsCommands, currentPath, fileSystem)
                    currentLsCommands = mutableListOf()
                }
                readingForLsCommand = false
                // get args
                val rawArgs = it.split(" ")
                val args = rawArgs.subList(1, rawArgs.size)

                if (args[0] == "cd") {
                    currentPath = doCd(args[1], currentPath, fileSystem)
                } else if (args[0] == "ls") {
                    readingForLsCommand = true
                }
            } else {
                if (!readingForLsCommand) {
                    throw RuntimeException("not reading for ls command?")
                }
                currentLsCommands += it.trim()
            }
        }
        if (readingForLsCommand) {
            doLs(
                currentLsCommands,
                currentPath,
                fileSystem
            )
        }

        println("File system: $fileSystem")

        val totalSums = mutableMapOf<String, Int>()
        for (dir in fileSystem) {
            totalSums[dir.key] = calculateMySizeAndMyChildren(dir.key, fileSystem, mutableListOf())
        }

        val curDiskSpace = FILE_SYSTEM_SIZE - totalSums["/"]!!
        val diskSpaceNeeded = UPDATE_SPACE_NEEDED - curDiskSpace
        var smallestDir = Optional.empty<Pair<String, Int>>()
        for (dir in totalSums) {
            if (dir.value >= diskSpaceNeeded) {
                if (smallestDir.isEmpty || smallestDir.get().second > dir.value) {
                    smallestDir = Optional.of(Pair(dir.key, dir.value))
                }
            }
        }

        // part 1
//        return totalSums.filter { it.value < 100000 }.values.sum()
        // part 2
        return smallestDir.get().second
    }

    fun calculateMySizeAndMyChildren(
        myPath: String,
        fileSystem: MutableMap<String, MutableList<Pair<String, Int>>>,
        processedDirs: MutableList<String>
    ): Int {
        if (processedDirs.contains(myPath)) return 0

        var mySum = 0
        for (file in fileSystem[myPath]!!) {
            mySum += file.second
        }

        processedDirs += myPath

        val myChildren = fileSystem.filter { dir -> dir.key.startsWith(myPath) && dir.key != myPath }
        myChildren.forEach { childDir ->
            mySum += calculateMySizeAndMyChildren(childDir.key, fileSystem, processedDirs)
        }
        return mySum
    }

    private fun isCommand(line: String): Boolean {
        return line.startsWith("$")
    }

    private fun buildFileTree(
        res: String,
        currentPath: String,
        fileSystem: MutableMap<String, MutableList<Pair<String, Int>>>
    ) {
        val thisLine = res.split(" ")
        if (thisLine[0] == "dir") {
            if (currentPath == "/") {
                fileSystem["$currentPath${thisLine[1]}"] = mutableListOf()
            } else {
                fileSystem["$currentPath/${thisLine[1]}"] = mutableListOf()
            }
        } else {
            val size = thisLine[0].toInt()
            fileSystem[currentPath]!!.add(Pair(thisLine[1], size))
        }
    }

    fun doCd(
        whereMove: String,
        currentPath: String,
        fileSystem: MutableMap<String, MutableList<Pair<String, Int>>>
    ): String {
        var newPath = currentPath

        // move active directory state
        if (whereMove == "..") {
            newPath = newPath.substringBeforeLast("/")
            // back to root
            if (newPath == "") {
                return "/"
            }
        } else if (whereMove == "/") {
            return "/"
        } else {
            newPath += if (newPath == "/") {
                whereMove
            } else {
                "/$whereMove"
            }

            if (!fileSystem.containsKey(newPath)) {
                fileSystem[newPath] = mutableListOf()
            }
        }

        return newPath
    }

    private fun doLs(
        lsResult: List<String>,
        currentPath: String,
        fileSystem: MutableMap<String, MutableList<Pair<String, Int>>>
    ) {
        for (res in lsResult) {
            buildFileTree(res, currentPath, fileSystem)
        }
    }
}
