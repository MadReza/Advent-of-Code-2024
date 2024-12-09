package day03

import println
import readInput

fun part1(lines: List<String>) =
    lines.asSequence()
        .flatMap {
            mul.toRegex()
                .findAll(it)
                .toList()
                .asSequence()
        }
        .map { it.value }
        .map { it.removePrefix("mul(") }
        .map { it.removeSuffix(")") }
        .map { it.split(",") }
        .map { (a, b) -> a.toInt() * b.toInt() }
        .sum()

const val mul = """mul\(\d{1,3},\d{1,3}\)"""
const val dos = """do\(\)"""
const val dont = """don't\(\)"""

fun part2(lines: List<String>): Long {
    var count = true
    var total = 0L
    "$mul|$dos|$dont".toRegex()
        .findAll(
            lines.joinToString()
        )
        .forEach {
            when (it.value) {
                "do()" -> count = true
                "don't()" -> count = false
                else -> {
                    if (count) {
                        val (a, b) = it.value
                            .removeSurrounding(prefix = "mul(",  suffix = ")")
                            .split(",")

                        total += a.toLong() * b.toLong()
                    }
                }
            }
        }
    return total
}





fun main() {
    val input = readInput("day03/day03")
    part1(input).println()
    part2(input).println()
}
