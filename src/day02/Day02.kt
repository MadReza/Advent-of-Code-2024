package day02

import println
import readInput
import kotlin.math.absoluteValue

fun part1(lines: List<String>): Long =
    lines.stream()
        .map { it.split("\\s+".toRegex()) }
        .map { it.map {num -> num.toInt() } }
        .map { isItSafe(it) }
        .filter { it } // Previous map could be replaced by filter.
        .count()

fun part2(lines: List<String>): Long =
    lines.stream()
        .map { it.split("\\s+".toRegex()) }
        .map { it.map {num -> num.toInt() } }
        .map {
            var safe = false
            for (i in 0..it.lastIndex) {
                safe = isItSafe(it.toMutableList().apply { removeAt(i) })
                if (safe) break
            }
            return@map safe
        }
        .filter { it }
        .count()


private fun isItSafe(it: List<Int>): Boolean {
    var lowering: Boolean? = null
    var broken = false
    it.iterator()
        .asSequence()
        .windowed(2, 1)
        .forEach { (first, second) ->
            lowering = if (lowering == null) first > second else lowering

            if (first > second != lowering) {
                broken = true
                return@forEach
            }

            val difference = (first - second).absoluteValue
            if (difference < 1 || difference > 3) {
                broken = true
                return@forEach
            }
        }

    return !broken
}




fun main() {
    val input = readInput("day02/day02")
    part1(input).println()
    part2(input).println()
}
