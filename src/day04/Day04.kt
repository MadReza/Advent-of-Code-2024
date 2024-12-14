package day04

import println
import readInput

fun part1(lines: List<String>): Int {
    val full = buildString{
        val leftToRight = lines.joinToString(separator = "|")   // →
        val rightToLeft = leftToRight.reversed() // ←
        append(leftToRight)
        append('|')
        append(rightToLeft)
        append('|')

        val down = down(lines)  // ↓
        val up = down(lines.reversed()) // ↑
        append('|')
        append(down)
        append('|')
        append(up)
        append('|')

        val diagDownRight = diagDownRight(lines)    // ↘
        append(diagDownRight)
        append('|')
        append(diagDownRight.reversed())            // ↖

        val diagUpRight = diagDownRight(lines.reversed()) // ↗
        append(diagUpRight)
        append('|')
        append(diagUpRight.reversed())                    // ↙

    }
    return "XMAS".toRegex()
        .findAll(full)
        .count()
}

fun diagDownRight(lines: List<String>): String {
    return buildString {
        for (start in 0..lines.lastIndex) {
            var positionOnLine = 0
            lines.subList(fromIndex = start, toIndex = lines.size)
                .forEach { line ->
                    append(line[positionOnLine])
                    positionOnLine++
            }
            append('|')

            positionOnLine = start + 1
            lines.subList(fromIndex = 0, toIndex = lines.size - start - 1)
                .forEach { line ->
                    append(line[positionOnLine])
                    positionOnLine++
                }
            append('|')
        }
    }
}

fun down(lines: List<String>): String {
    return buildString {
        lines[0].forEachIndexed { index, _ ->
            lines.forEach {
                append(it[index])
            }
            append("|")
        }
    }
}


fun part2(lines: List<String>): Int {
    var found = 0
    for (x in 0..lines[0].lastIndex) {
        for (y in 0..lines.lastIndex) {
            if ('a'.equals(lines[x][y], true)
                && checkSurroundingForMAS(lines, x, y)) {
                found++
            }
        }
    }
    return found
}

fun checkSurroundingForMAS(lines: List<String>, x: Int, y: Int): Boolean {
    try {
        val topLeft = lines[x-1][y-1]
        val topRight = lines[x-1][y+1]
        val bottomLeft = lines[x+1][y-1]
        val bottomRight = lines[x+1][y+1]

        val full = "$topLeft$bottomRight$topRight$bottomLeft"
//        println(full)

        if ("msms".equals(full, true) ||
            "smsm".equals(full, true) ||
            "mssm".equals(full, true) ||
            "smms".equals(full, true)) {

            return true
        }

    } catch (_: IndexOutOfBoundsException) {
        return false
    }
    return false
}





fun main() {
    val input = readInput("day04/day04")
    part1(input).println()
    part2(input).println()
}
