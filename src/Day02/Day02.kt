package Day02

import println
import readInput
import kotlin.math.abs

fun isSafeRow(numbers: List<Long>): Boolean {
    val range = 1..3
    var isIncreasing = true
    var isDecreasing = true

    for (i in 0 until numbers.size - 1) {
        val diff = abs(numbers[i] - numbers[i + 1])

        if (diff !in range) {
            return false
        }

        if (numbers[i] < numbers[i + 1]) {
            isDecreasing = false
        } else if (numbers[i] > numbers[i + 1]) {
            isIncreasing = false
        }
    }

    return isIncreasing != isDecreasing
}

fun main() {
    fun part1(input: List<String>): Long {
        var safeRows = 0L

        input.forEach { line ->
            val numbers = line.split(" ").map { it.toLong() }
            if (isSafeRow(numbers)) {
                safeRows++
            }
        }

        return safeRows
    }

    fun part2(input: List<String>): Long {
        var safeRows = 0
        safeRows = input.count { line ->
            val numbers = line.split(" ").map { it.toLong() }

            if (isSafeRow(numbers)) {
                return@count true
            }

            (numbers.indices).any { i ->
                val report = numbers.toMutableList()
                report.removeAt(i)
                isSafeRow(report)
            }
        }
        return safeRows.toLong()
    }

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test", "Day02")
    check(part1(testInput) == 2L)

    val testInput2 = readInput("Day02_test", "Day02")
    check(part2(testInput2) == 4L)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02", "Day02")
    part1(input).println()
    part2(input).println()
}
