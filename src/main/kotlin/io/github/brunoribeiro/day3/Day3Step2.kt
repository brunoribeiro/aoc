package io.github.brunoribeiro.day3

import java.io.File

fun main(){

    val result = File("input_3.txt").readLines()
        .chunked(3)
        .map { chunks ->
            chunks[0].filter { chunks[1].contains(it) && chunks[2].contains(it) }.toCharArray().distinct()
        }.sumOf { Rucksack.priorities(it) }


    println(result)

}

