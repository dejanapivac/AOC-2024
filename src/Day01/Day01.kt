package Day01

import println
import readInput
import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
        val queue1 = PriorityQueue<Long>()
        val queue2 = PriorityQueue<Long>()
        var totalDistance = 0L

        for (line in input) {
            queue1.add(line.substringBefore(" ").toLong())
            queue2.add(line.substringAfterLast(" ").toLong())
        }

        while(!queue1.isEmpty()) {
            totalDistance += abs(queue1.poll() - queue2.poll())
        }

        return totalDistance
    }

    fun part2(input: List<String>): Long {
        val queue1 = PriorityQueue<Long>()
        val queue2 = PriorityQueue<Long>()
        val frequencyMap = mutableMapOf<Long, Int>()
        var totalDistance = 0L
        var similarityScore = 0L

        for (line in input) {
            val firstElement = line.substringBefore(" ").toLong()
            val secondElement = line.substringAfterLast(" ").toLong()
            queue1.add(firstElement)
            queue2.add(secondElement)
            frequencyMap[secondElement] = frequencyMap.getOrDefault(secondElement, 0) + 1
        }

        while(!queue1.isEmpty()) {
            val element = queue1.poll()
            similarityScore += element * (frequencyMap[element] ?: 0)
            totalDistance += abs(element - queue2.poll())
        }

        return similarityScore
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test", "Day01")
    check(part1(testInput) == 11L)

    val testInput2 = readInput("Day01_test", "Day01")
    check(part2(testInput2) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01", "Day01")
    part1(input).println()
    part2(input).println()
}
