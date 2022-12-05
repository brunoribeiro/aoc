package io.github.brunoribeiro.day3

import java.io.File

fun main() {

    val result = File("input_3.txt").readLines()
        .map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
        .map { (left, right) -> left.filter { right.contains(it) }.toCharArray().distinct() }
        .sumOf { Rucksack.priorities(it) }

    println(result)

}

