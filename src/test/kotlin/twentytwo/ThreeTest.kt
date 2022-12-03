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
}