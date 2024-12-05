package Day03

import println
import readInput


fun patternMatch(input: List<String>): Sequence<MatchResult> {
    val pattern = Regex("""mul\((\d{1,3},\d{1,3})\)""")
    return input.asSequence().flatMap { line -> pattern.findAll(line) }
}

fun findMatch(input: List<String>): List<Pair<Int, Int>> {
    val regex = Regex("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3},\\d{1,3})\\)")
    var dont = false
    val pairs = mutableListOf<Pair<Int, Int>>()

    input.forEach { line ->
        regex.findAll(line).forEach { match ->
            when (match.value) {
                "do()" -> {
                    dont = false
                }

                "don't()" -> {
                    dont = true
                }

                else -> if (match.value.startsWith("mul(")) {
                    if (dont) {
                        return@forEach
                    } else {
                        val numbers = match.groups[1]?.value?.split(",")?.map { it.toInt() }
                        if (numbers != null && numbers.size == 2) {
                            pairs.add(Pair(numbers[0], numbers[1]))
                        }
                    }
                }
            }
        }
    }

    return pairs
}


fun main() {
    fun part1(input: List<String>): Long {
        val matches = patternMatch(input)
        val sumOfAll = matches.map { match ->
            val numbers = match.groupValues[1]
                .split(",")
                .map { it.toLong() }
            numbers[0] * numbers[1]
        }.sum()

        return sumOfAll
    }

    fun part2(input: List<String>): Long {
        val allPairs = findMatch(input) // Process all lines in one go
        return allPairs.sumOf { (a, b) -> a.toLong() * b.toLong() }
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test", "Day03")
    check(part1(testInput) == 161L)

    val testInput2 = readInput("Day03_test", "Day03")
//    check(part2(testInput2) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03", "Day03")
    part1(input).println()
    part2(input).println()
}
