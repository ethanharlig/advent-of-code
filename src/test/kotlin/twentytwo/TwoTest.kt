package twentytwo

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TwoTest {

    @Test
    fun movesAreEqual() {

        assertEquals(Two.Moves.ROCK, Two.Moves.ROCK)
        assertEquals(Two.Moves.PAPER, Two.Moves.PAPER)
        assertEquals(Two.Moves.SCISSORS, Two.Moves.SCISSORS)
    }

    @Test
    fun parsesInput() {
        assertEquals(Two.Moves.ROCK, Two.Moves.fromInput("A"))
        assertEquals(Two.Moves.ROCK, Two.Moves.fromInput("X"))
        assertEquals(Two.Moves.PAPER, Two.Moves.fromInput("B"))
        assertEquals(Two.Moves.PAPER, Two.Moves.fromInput("Y"))
        assertEquals(Two.Moves.SCISSORS, Two.Moves.fromInput("C"))
        assertEquals(Two.Moves.SCISSORS, Two.Moves.fromInput("Z"))
    }

    @Test
    fun determinesGameScore() {
        assertEquals(0, Two.TerminalGameState.determineGameStateScore(Two.Moves.PAPER, Two.Moves.ROCK).scoreValue)
        assertEquals(0, Two.TerminalGameState.determineGameStateScore(Two.Moves.SCISSORS, Two.Moves.PAPER).scoreValue)
        assertEquals(0, Two.TerminalGameState.determineGameStateScore(Two.Moves.ROCK, Two.Moves.SCISSORS).scoreValue)

        assertEquals(3, Two.TerminalGameState.determineGameStateScore(Two.Moves.ROCK, Two.Moves.ROCK).scoreValue)
        assertEquals(3, Two.TerminalGameState.determineGameStateScore(Two.Moves.PAPER, Two.Moves.PAPER).scoreValue)
        assertEquals(
            3,
            Two.TerminalGameState.determineGameStateScore(Two.Moves.SCISSORS, Two.Moves.SCISSORS).scoreValue
        )

        assertEquals(6, Two.TerminalGameState.determineGameStateScore(Two.Moves.ROCK, Two.Moves.PAPER).scoreValue)
        assertEquals(6, Two.TerminalGameState.determineGameStateScore(Two.Moves.PAPER, Two.Moves.SCISSORS).scoreValue)
        assertEquals(6, Two.TerminalGameState.determineGameStateScore(Two.Moves.SCISSORS, Two.Moves.ROCK).scoreValue)
    }
}
