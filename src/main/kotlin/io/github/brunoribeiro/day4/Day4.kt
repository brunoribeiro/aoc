package io.github.brunoribeiro.day4

import java.io.File

fun main() {

    val lines = File("input_4.txt").readLines()
    val result = lines.map { pairs ->
            pairs.split(",").map { pair ->
                pair.split("-").map { digit -> digit.toInt() }
            }.map { intPair -> (intPair[0]..intPair[1]) }
        }.filter { sections ->
            sections.sortedByDescending { section -> section.last - section.first }.let { sortedSections ->
                sortedSections[0].first <= sortedSections[1].first && sortedSections[0].last >= sortedSections[1].last
            }
        }.size

    println(result)

    val result2 = lines.map { pairs ->
            pairs.split(",").map { pair ->
                pair.split("-").map { digit -> digit.toInt() }
            }.map { intPair -> (intPair[0]..intPair[1]) }
        }.map { sections ->
            sections[0].filter {
                    pos -> pos >= sections[1].first && pos <= sections[1].last
            } + sections[1].filter {
                    pos -> pos >= sections[0].first && pos <= sections[0].last
            }
        }.filter { it.isNotEmpty() }
        .size


    println(result2)
}