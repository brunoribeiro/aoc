package io.github.brunoribeiro.day2

import io.github.brunoribeiro.day2.Day2Step2.calculateMyMoveByOutcome
import io.github.brunoribeiro.day2.Day2Step2.toOpponentMove
import java.io.File


object Day2Step2 {

    fun toOpponentMove(code: String): Move {
        return when (code) {
            "A" -> Move.ROCK
            "B" -> Move.PAPER
            "C" -> Move.SCISSORS
            else -> throw IllegalArgumentException()
        }
    }


    fun calculateMyMoveByOutcome(opponentMove: Move, outcomeCode: String): Move {

        val strategies = mapOf(
            (Move.ROCK to Outcome.WIN) to Move.PAPER,
            (Move.ROCK to Outcome.DRAW) to Move.ROCK,
            (Move.ROCK to Outcome.LOST) to Move.SCISSORS,
            (Move.PAPER to Outcome.WIN) to Move.SCISSORS,
            (Move.PAPER to Outcome.DRAW) to Move.PAPER,
            (Move.PAPER to Outcome.LOST) to Move.ROCK,
            (Move.SCISSORS to Outcome.WIN) to Move.ROCK,
            (Move.SCISSORS to Outcome.DRAW) to Move.SCISSORS,
            (Move.SCISSORS to Outcome.LOST) to Move.PAPER,
        )

        val expectedOutcome = when (outcomeCode) {
            "X" -> Outcome.LOST
            "Y" -> Outcome.DRAW
            "Z" -> Outcome.WIN
            else -> throw IllegalArgumentException()
        }

        return strategies[opponentMove to expectedOutcome] ?: throw IllegalArgumentException()

    }
}

fun main() {

    val lines = File("input_2.txt").readLines()

    val result = lines.map {
        it.split(" ").let {

            val opponentMove = toOpponentMove(it[0])
            val outcomeCode = it[1]
            val myMove = calculateMyMoveByOutcome(opponentMove, outcomeCode)

            Round(opponentMove, myMove)
        }
    }.sumOf { it.points() }

    println(result)

}





