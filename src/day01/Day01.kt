package day01

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(lines: List<String>): Int {
        val firstMutableList: MutableList<Int> = mutableListOf()
        val secondMutableList: MutableList<Int> = mutableListOf()
        lines.map {
            val (first, second) = it.split("\\s+".toRegex())
            firstMutableList.add(first.toInt())
            secondMutableList.add(second.toInt())
        }
        firstMutableList.sort()
        secondMutableList.sort()

        var sum = 0
        (0..<firstMutableList.size).forEach {
            sum += abs(secondMutableList[it] - firstMutableList[it])
        }

        return sum
    }

    fun part2(lines: List<String>): Long {
        val (left, right) = lines.map {
            val (firstWord, secondWord) = it.split("""\s+""".toRegex())
            firstWord.toLong() to secondWord.toLong()
        }.unzip()

        val frequencies = right.groupingBy { it }.eachCount()

        return left.fold(0L) { acc, num ->
            acc + num * frequencies.getOrDefault(num, 0)
        }

    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01/Day01")
    part1(input).println()
    part2(input).println()
}
