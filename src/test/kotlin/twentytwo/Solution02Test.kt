package twentytwo

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class Solution02Test {

    @Test
    fun movesAreEqual() {

        assertEquals(Solution02.Moves.ROCK, Solution02.Moves.ROCK)
        assertEquals(Solution02.Moves.PAPER, Solution02.Moves.PAPER)
        assertEquals(Solution02.Moves.SCISSORS, Solution02.Moves.SCISSORS)
    }

    @Test
    fun parsesInput() {
        assertEquals(Solution02.Moves.ROCK, Solution02.Moves.fromInput("A"))
        assertEquals(Solution02.Moves.ROCK, Solution02.Moves.fromInput("X"))
        assertEquals(Solution02.Moves.PAPER, Solution02.Moves.fromInput("B"))
        assertEquals(Solution02.Moves.PAPER, Solution02.Moves.fromInput("Y"))
        assertEquals(Solution02.Moves.SCISSORS, Solution02.Moves.fromInput("C"))
        assertEquals(Solution02.Moves.SCISSORS, Solution02.Moves.fromInput("Z"))
    }

    @Test
    fun determinesGameScore() {
        assertEquals(0, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.PAPER, Solution02.Moves.ROCK).scoreValue)
        assertEquals(0, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.SCISSORS, Solution02.Moves.PAPER).scoreValue)
        assertEquals(0, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.ROCK, Solution02.Moves.SCISSORS).scoreValue)

        assertEquals(3, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.ROCK, Solution02.Moves.ROCK).scoreValue)
        assertEquals(3, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.PAPER, Solution02.Moves.PAPER).scoreValue)
        assertEquals(
            3,
            Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.SCISSORS, Solution02.Moves.SCISSORS).scoreValue
        )

        assertEquals(6, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.ROCK, Solution02.Moves.PAPER).scoreValue)
        assertEquals(6, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.PAPER, Solution02.Moves.SCISSORS).scoreValue)
        assertEquals(6, Solution02.TerminalGameState.determineGameStateScore(Solution02.Moves.SCISSORS, Solution02.Moves.ROCK).scoreValue)
    }
}
