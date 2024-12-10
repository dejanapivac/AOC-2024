package Day05

import println
import readTxt

fun correctlyOrderedUpdate(rules: HashMap<Int, MutableList<Int>>, pageNumbersList: List<MutableList<Int>>): Long {
    var result = 0L
    outerLoop@ for (list in pageNumbersList) {
        for (i in 0 until list.size - 1) {
            for (j in i + 1 until list.size) {
                val current = list[i]
                val next = list[j]
                if (!rules.containsKey(current) || rules[current]?.contains(next) == false) {
                    continue@outerLoop
                }

            }
        }
        val middleElement = list[(list.size / 2)]
        result += middleElement
    }
    return result
}

fun orderCorrectlyAndAdd(rules: HashMap<Int, MutableList<Int>>, pageNumbersList: List<MutableList<Int>>): Long {
    var result = 0L
    outerLoop@ for (list in pageNumbersList) {
        var isIncorrect = false
        for (i in 0 until list.size - 1) {
            for (j in i + 1 until list.size) {
                val current = list[i]
                val next = list[j]
                if (!rules.containsKey(current) || rules[current]?.contains(next) == false) {
                    isIncorrect = true
                    list[i] = next
                    list[j] = current
                }
            }
        }
        if (isIncorrect) {
            val middleElement = list[(list.size / 2)]
            result += middleElement
        }
    }
    return result
}

fun main() {
    fun part1(input: String): Long {
        val rules = HashMap<Int, MutableList<Int>>()
        val (rulesSection, numbersSection) = input.split("\r\n\r\n")

        rulesSection.lines().forEach { line ->
            val (key, value) = line.split("|").map { it.toInt() }
            rules.computeIfAbsent(key) { mutableListOf() }.add(value)
        }

        val pageNumbers = numbersSection.lines().map { line ->
            line.split(",").map { it.toInt() }.toMutableList()
        }

        return correctlyOrderedUpdate(rules, pageNumbers)
    }

    fun part2(input: String): Long {
        val rules = HashMap<Int, MutableList<Int>>()
        val (rulesSection, numbersSection) = input.split("\r\n\r\n")

        rulesSection.lines().forEach { line ->
            val (key, value) = line.split("|").map { it.toInt() }
            rules.computeIfAbsent(key) { mutableListOf() }.add(value)
        }

        val pageNumbers = numbersSection.lines().map { line ->
            line.split(",").map { it.toInt() }.toMutableList()
        }

        return orderCorrectlyAndAdd(rules, pageNumbers)
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
