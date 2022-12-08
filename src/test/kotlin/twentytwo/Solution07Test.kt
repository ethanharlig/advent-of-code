package twentytwo

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Solution07Test {
    private lateinit var seven: Solution07

    private lateinit
    var fileSystem: MutableMap<String, MutableList<Pair<String, Int>>>

    @Before
    fun beforeEach() {
        seven = Solution07()
        fileSystem = mutableMapOf()
    }

    @Test
    fun changesDir() {
        assertEquals("/", seven.doCd("..", "/test", fileSystem))
        assertEquals("/test", seven.doCd("test", "/", fileSystem))
        assertEquals("/test/123/abc123/321cba/wah", seven.doCd("wah", "/test/123/abc123/321cba", fileSystem))
        assertEquals("/", seven.doCd("/", "/test/another/third/so/far", fileSystem))
    }

    @Test
    fun canCalculateMySizeAndChildren() {
        val seven = Solution07()
        val fileSystem = mutableMapOf<String, MutableList<Pair<String, Int>>>()
        fileSystem["/"] = mutableListOf(Pair("slash", 100), Pair("roott.txt", 200))
        fileSystem["/one"] = mutableListOf(Pair("one", 100), Pair("one.txt", 200))
        fileSystem["/one/two"] = mutableListOf(Pair("onetwo.txt", 100), Pair("onete.txt", 200))
        fileSystem["/one/three"] = mutableListOf(Pair("onethree.txt", 100), Pair("onethre.txt", 200))
        fileSystem["/four"] = mutableListOf(Pair("four.txt", 100), Pair("four.txt", 200))
        fileSystem["/four/one"] = mutableListOf(Pair("fourone.txt", 100), Pair("fourthre.txt", 200))
        fileSystem["/four/two"] = mutableListOf(Pair("fourtwo.txt", 100), Pair("fourtqwo.txt", 200))
        fileSystem["/four/five/six/seven"] = mutableListOf(Pair("fourfive...txt", 100), Pair("fourel.txt", 200))

        assertEquals(8 * 100 + 8 * 200, seven.calculateMySizeAndMyChildren("/", fileSystem, mutableListOf()))
        assertEquals(
            6 * 100 + 6 * 200,
            seven.calculateMySizeAndMyChildren("/", fileSystem, mutableListOf("/one", "/one/three"))
        )

        assertEquals(3 * 100 + 3 * 200, seven.calculateMySizeAndMyChildren("/one", fileSystem, mutableListOf()))
        assertEquals(1 * 100 + 1 * 200, seven.calculateMySizeAndMyChildren("/one/two", fileSystem, mutableListOf()))
        assertEquals(1 * 100 + 1 * 200, seven.calculateMySizeAndMyChildren("/one/three", fileSystem, mutableListOf()))
        assertEquals(4 * 100 + 4 * 200, seven.calculateMySizeAndMyChildren("/four", fileSystem, mutableListOf()))
        assertEquals(1 * 100 + 1 * 200, seven.calculateMySizeAndMyChildren("/four/one", fileSystem, mutableListOf()))
        assertEquals(1 * 100 + 1 * 200, seven.calculateMySizeAndMyChildren("/four/two", fileSystem, mutableListOf()))
        assertEquals(
            1 * 100 + 1 * 200,
            seven.calculateMySizeAndMyChildren(
                "/four/five/six/seven",
                fileSystem,
                mutableListOf()
            )
        )
    }
}
