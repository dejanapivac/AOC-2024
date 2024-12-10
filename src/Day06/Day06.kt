package Day06

import println
import readTxt

fun main() {
    fun part1(input: String): Long {
        return 0L
    }

    fun part2(input: String): Long {
        return 0L
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readTxt("Day05_test", "Day05")
    part1(testInput).println()
//    println("part 2 test: ${part2(testInput)}")
//    check(part1(testInput) == 143L)
//    check(part2(testInput) == 9L)

    val testInput2 = readTxt("Day05_test", "Day05")
    part2(testInput2).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readTxt("Day05", "Day05")
    part1(input).println()
    println("part 2: ${part2(input)}")
}
