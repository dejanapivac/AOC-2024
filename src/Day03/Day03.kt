package Day03

import println
import readInput


fun main() {
    fun part1(input: List<String>): Long {
        return 161L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test", "Day03")
    check(part1(testInput) == 161L)

    val testInput2 = readInput("Day03_test", "Day03")
//    check(part2(testInput2) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03", "Day03")
    part1(input).println()
//    part2(input).println()
}
