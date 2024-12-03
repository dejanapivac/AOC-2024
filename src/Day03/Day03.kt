package Day03

import println
import kotlin.io.path.Path
import kotlin.io.path.readText

fun findFunction(numbers: String): Sequence<MatchResult> {
    val pattern = Regex("""mul\((\d{1,3},\d{1,3})\)""")
    return pattern.findAll(numbers)
}


fun main() {
    fun part1(input: String): Long {
        val matches = findFunction(input)
        val sumOfAll = matches.map { match ->
            val numbers = match.groupValues[1]
                .split(",")
                .map { it.toLong() }
            numbers[0] * numbers[1]
        }.sum()

        return sumOfAll
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    fun readInput(name: String, folder: String): String {
        return Path("src/$folder/$name.txt").readText().trim()
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
