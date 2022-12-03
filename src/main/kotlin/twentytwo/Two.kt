package twentytwo

import java.lang.RuntimeException

class Two {
    enum class Moves(val scoreValue: Int) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        companion object {
            fun fromInput(input: String): Moves {
                if (input == "A" || input == "X") {
                    return ROCK
                } else if (input == "B" || input == "Y") {
                    return PAPER
                } else if (input == "C" || input == "Z") {
                    return SCISSORS
                }
                throw RuntimeException("shit!")
            }

            fun determineMove(other: Moves, terminalGameState: TerminalGameState): Moves {
                return when(terminalGameState) {
                    TerminalGameState.LOSE -> {
                        when (other) {
                            ROCK -> SCISSORS
                            PAPER -> ROCK
                            SCISSORS -> PAPER
                        }
                    }
                    TerminalGameState.DRAW -> {
                        when (other) {
                            ROCK -> ROCK
                            PAPER -> PAPER
                            SCISSORS -> SCISSORS
                        }
                    }
                    TerminalGameState.WIN -> {
                        when (other) {
                            ROCK -> PAPER
                            PAPER -> SCISSORS
                            SCISSORS -> ROCK
                        }
                    }
                }
            }
        }
    }

    enum class TerminalGameState(val scoreValue: Int) {
        LOSE(0),
        DRAW(3),
        WIN(6);

        companion object {
            fun fromInput(input: String): TerminalGameState {
                if (input == "X") {
                    return LOSE
                } else if (input == "Y") {
                    return DRAW
                } else if (input == "Z") {
                    return WIN
                }
                throw RuntimeException("shit!")
            }

            fun determineGameStateScore(other: Moves, me: Moves): TerminalGameState {
                if (other.scoreValue == me.scoreValue) {
                    return DRAW
                }
                if (me.scoreValue - 1 == other.scoreValue || me == Moves.ROCK && other == Moves.SCISSORS) {
                    return WIN
                }
                return LOSE
            }
        }
    }

    fun main() {
        val myTestScore = doStuff("/src/main/resources/twentytwo/two/test.txt")
        println(myTestScore)

        val myTotalScore = doStuff("/src/main/resources/twentytwo/two/actual.txt")
        println(myTotalScore)
    }

    private fun doStuff(filePath: String): Int {
        var score = 0

        FileUtil.getFileRelativeToRoot(filePath).forEachLine {
            val values = it.split(" ")
            val opponent = Moves.fromInput(values[0])
            // part 1
            // val mine = Moves.fromInput(values[1])
            // score += TerminalGameState.determineGameStateScore(opponent, mine).scoreValue

            // part 2
            val mine = TerminalGameState.fromInput(values[1])
            score += Moves.determineMove(opponent, mine).scoreValue
            score += mine.scoreValue
        }
        return score
    }
}
