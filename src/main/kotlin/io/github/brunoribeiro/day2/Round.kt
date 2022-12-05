package io.github.brunoribeiro.day2

data class Round(val opponent: Move, val me: Move) {

    private val outcomes = mapOf(
        (Move.ROCK to Move.ROCK) to Outcome.DRAW,
        (Move.ROCK to Move.PAPER) to Outcome.WIN,
        (Move.ROCK to Move.SCISSORS) to Outcome.LOST,
        (Move.PAPER to Move.ROCK) to Outcome.LOST,
        (Move.PAPER to Move.PAPER) to Outcome.DRAW,
        (Move.PAPER to Move.SCISSORS) to Outcome.WIN,
        (Move.SCISSORS to Move.ROCK) to Outcome.WIN,
        (Move.SCISSORS to Move.PAPER) to Outcome.LOST,
        (Move.SCISSORS to Move.SCISSORS) to Outcome.DRAW
    )


    fun points(): Int {
        val outcomePoints = outcomes[opponent to me]?.points ?: 0
        val selectedMovePoints = me.points
        return outcomePoints + selectedMovePoints
    }
}