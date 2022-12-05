package io.github.brunoribeiro.day3


object Rucksack {
    fun priorities(chars: List<Char>): Int {
        return chars.sumOf {
            when (it.isLowerCase()) {
                true -> it.code - 96
                else -> it.code - 38
            }
        }
    }
}