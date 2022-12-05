package io.github.brunoribeiro.day5

import java.io.File
import java.util.InputMismatchException


fun main() {

    val lines = File("input_5.txt").readLines()

    val map = lines.take(8)
        .map {
            it.chunked(4)
                .map { it.replace("]", "").replace("[", "").replace(" ", "") }
        }.fold(mutableMapOf<Int, MutableList<String>>()) { acc, curr ->
            curr.foldIndexed(acc) { index, innerAcc, str ->
                when (innerAcc.contains(index + 1)) {
                    true -> innerAcc[index + 1]?.add(str)
                    else -> {
                        innerAcc[index + 1] = mutableListOf(str)
                    }
                }
                innerAcc
            }
        }

    lines.subList(10, lines.size)
        .map { it.replace("move", "").split("from").flatMap { it.split("to") }.map { it.trim() } }
        .map { Move(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        .forEach {

            val fromStack = map[it.from].orEmpty().filter { it.isNotBlank() }
            val targetStack = map[it.to].orEmpty().filter { it.isNotBlank() }

            map[it.to] = (fromStack.take(it.amount) //.reversed() uncomment for part2 result
                    + targetStack) as MutableList<String>
            map[it.from] = fromStack.drop(it.amount).toMutableList()

        }.also {
            println(map.values.joinToString("") { it[0] })
        }



}

data class Move(val amount: Int, val from: Int, val to: Int)