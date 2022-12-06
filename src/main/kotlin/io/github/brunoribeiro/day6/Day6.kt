package io.github.brunoribeiro.day6

import java.io.File

fun main() {
    File("input_6.txt").readText().let { dataStream ->
        println(findStartIndex(dataStream, 4))
        println(findStartIndex(dataStream, 14))
    }
}

fun findStartIndex(dataStream: String, markerSize: Int): Int = (0..dataStream.length - markerSize)
    .indexOfFirst { idx -> dataStream.substring(idx..idx + (markerSize - 1)).toList().distinct().size == markerSize }
    .let { index -> index + markerSize }

