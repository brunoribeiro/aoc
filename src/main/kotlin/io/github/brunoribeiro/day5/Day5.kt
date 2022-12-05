package io.github.brunoribeiro.day5

import java.io.File
import java.util.InputMismatchException


fun main(){

    val lines =  File("input_5.txt").readLines()
    val cargo = mutableMapOf<Int,MutableList<String>>()
   lines.take(8)
        .map {it.chunked(4)
            .map { it.replace("]","").replace("[","").replace(" ","") }
        }
        .forEach {
            it.forEachIndexed { index, str ->
                when(cargo.contains(index + 1)){
                    true -> cargo[index + 1]?.add(str)
                    else -> {
                        cargo[index + 1] = mutableListOf(str)
                    }
                }
            }
        }


    val moves =  lines.subList(10,lines.size)

    moves
        .map { it.replace("move","").split("from").flatMap { it.split("to") }.map { it.trim() } }
        .map { Move(it[0].toInt(),it[1].toInt(),it[2].toInt()) }
        .forEach {

            val fromStack = cargo[it.from].orEmpty().filter { it.isNotBlank() }
            val targetStack = cargo[it.to].orEmpty().filter { it.isNotBlank() }

            cargo[it.to] = ( fromStack.take(it.amount) //.reversed() uncomment for part2 result
                    + targetStack) as MutableList<String>
            cargo[it.from] = fromStack.drop(it.amount).toMutableList()

        }

    println(cargo.values.joinToString("") { it[0] })


}

data class Move(val amount:Int, val from: Int, val to:Int)