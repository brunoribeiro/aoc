package io.github.brunoribeiro.day5

import java.io.File

fun main() {

    data class Move(val amount: Int, val from: Int, val to: Int)

    File("input_5.txt").readLines().let{ lines ->
        lines.take(8)
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
            }.map { it.key to it.value.filter { it.isNotBlank() } }.toMap()
            .let { map ->
                lines.subList(10, lines.size)
                    .map { it.replace("move", "").split("from").flatMap { it.split("to") }.map { it.trim() } }
                    .map { Move(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
                    .fold(map.toMutableMap()) { acc, curr ->
                        acc[curr.to] = (map[curr.from].orEmpty().take(curr.amount) //.reversed() uncomment for part2 result
                                + map[curr.to].orEmpty()) as MutableList<String>
                        acc[curr.from] = map[curr.from].orEmpty().drop(curr.amount).toMutableList()
                        acc
                    }
            }
    }.also {
        println(it.values.joinToString("") { it[0] })
    }

}

