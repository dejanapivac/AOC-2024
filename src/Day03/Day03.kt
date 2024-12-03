package Day03

import println
import readInput

fun findFunction(input: List<String>): List<Pair<Long, Long>> {
    val results = mutableListOf<Pair<Long, Long>>()

    input.forEach { line ->
        var index = 0
        while (index < line.length) {
            val start = line.indexOf("mul(", index)
            if (start == -1) break

            val end = line.indexOf(")", start)
            if (end != -1 && end == line.indexOf(")", start)) {
                val mulContent = line.substring(start + 4, end)

                val splited = mulContent.split(",")
                if (splited.size == 2) {
                    val num1String = splited[0].trim()
                    val num2String = splited[1].trim()

                    if (num1String.length in 1..3 && num2String.length in 1..3) {
                        val num1 = num1String.toLongOrNull()
                        val num2 = num2String.toLongOrNull()

                        if (num1 != null && num2 != null) {
                            results.add(Pair(num1, num2))
                        }
                    }
                }
            }
            index = start + 1
        }
    }

    return results
}
fun part1(input: List<String>): Long {
    val listOfPairs = findFunction(input)
    var result = 0L
    for (pair in listOfPairs) {
        result += (pair.first * pair.second)
    }
    return result
}


fun main() {
    fun part1(input: List<String>): Long {
        var listOfPairs = findFunction(input)
        var result = 0L
        for(pair in listOfPairs) {
            result += (pair.first * pair.second)
        }

        return result
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

//    fun readInput(name: String, folder: String): String {
//        return Path("src/$folder/$name.txt").readText().trim()
//    }

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
