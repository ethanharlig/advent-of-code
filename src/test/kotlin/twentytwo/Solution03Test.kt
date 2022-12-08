package twentytwo

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Solution03Test {

    @Test
    fun priorityValues() {
        val solution03 = Solution03()
        assertEquals(1, solution03.getPriorityValue('a'))
        assertEquals(26, solution03.getPriorityValue('z'))
        assertEquals(27, solution03.getPriorityValue('A'))
        assertEquals(52, solution03.getPriorityValue('Z'))
    }

    @Test
    fun commonType() {
        val solution03 = Solution03()
        assertEquals('p', solution03.findCommonType("vJrwpWtwJgWr", "hcsFMMfFFhFp"))
        assertEquals('L', solution03.findCommonType("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"))
        assertEquals('P', solution03.findCommonType("PmmdzqPrV", "vPwwTWBwg"))
    }

    @Test
    fun getStringsAndCommonType() {
        val solution03 = Solution03()
        val strings = listOf(
            Pair('v', "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"),
            Pair('t', "ttgJtRGJQctTZtZT"),
            Pair('s', "CrZsJsPPZsGzwwsLwLmpwMDw")
        )

        strings.forEach {
            val pairStrings = solution03.getTwoStrings(it.second)
            assertEquals(it.first, solution03.findCommonType(pairStrings.first, pairStrings.second))
        }
    }
}
