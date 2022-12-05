package io.github.brunoribeiro.day1

import java.io.File

fun main() {

    val byElf = File("input_1.txt").readText().split("\n\n")
        .map { it.split("\n").filter { it.isNotBlank() }.sumOf { it.toInt() } }

    println(byElf.max())
    println(byElf.sortedDescending().take(3).sum())

}

