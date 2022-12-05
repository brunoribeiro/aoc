package io.github.brunoribeiro.day2

import io.github.brunoribeiro.day2.Day2.toMove
import java.io.File

object Day2 {

    fun toMove(code: String): Move {
        return when (code) {
            "A", "X" -> Move.ROCK
            "B", "Y" -> Move.PAPER
            "C", "Z" -> Move.SCISSORS
            else -> throw IllegalArgumentException()
        }
    }

}

fun main() {

    val lines = File("input_2.txt").readLines()

    val result = lines.map {

        it.split(" ")
            .map { code -> toMove(code) }
            .let { (opponentMove, myMove) -> Round(opponentMove, myMove) }

    }.sumOf { it.points() }

    println(result)

}







