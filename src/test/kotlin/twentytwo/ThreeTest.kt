package twentytwo

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ThreeTest {

    @Test
    fun priorityValues() {
        val three = Three()
        assertEquals(1, three.getPriorityValue('a'))
        assertEquals(26, three.getPriorityValue('z'))
        assertEquals(27, three.getPriorityValue('A'))
        assertEquals(52, three.getPriorityValue('Z'))
    }

    @Test
    fun commonType() {
        val three = Three()
        assertEquals('p', three.findCommonType("vJrwpWtwJgWr", "hcsFMMfFFhFp"))
        assertEquals('L', three.findCommonType("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"))
        assertEquals('P', three.findCommonType("PmmdzqPrV", "vPwwTWBwg"))
    }

    @Test
    fun getStringsAndCommonType() {
        val three = Three()
        val strings = listOf(Pair('v', "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"), Pair('t', "ttgJtRGJQctTZtZT"), Pair('s', "CrZsJsPPZsGzwwsLwLmpwMDw"))

        strings.forEach {
            val pairStrings = three.getTwoStrings(it.second)
            assertEquals(it.first, three.findCommonType(pairStrings.first, pairStrings.second))
        }
    }
}