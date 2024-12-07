package Day04

import println
import readInput

val directions = listOf(
    // (x, y)
    intArrayOf(-1, 0),  // up
    intArrayOf(-1, 1), // top right
    intArrayOf(0, 1),  // right
    intArrayOf(1, 1),  // bottom-right
    intArrayOf(1, 0), // down
    intArrayOf(1, -1), // bottom-left
    intArrayOf(0, -1), // left
    intArrayOf(-1, -1) // top-left
)

const val xmas = "XMAS"

fun findXmas(
    grid: List<CharArray>,
    row: Int,
    col: Int,
    index: Int,
    direction: IntArray
): Boolean {
    // 1. off the map
    if (row < 0 || col < 0 || row >= grid.size || col >= grid[0].size) return false

    // 2. not next letter
    if (grid[row][col] != xmas[index]) return false

    // 3. found "s" - last char
    if (index == xmas.length - 1) return true

    val nextRow = row + direction[0]
    val nextCol = col + direction[1]

    return findXmas(grid, nextRow, nextCol, index + 1, direction)
}

fun countXmas(grid: List<CharArray>): Int {
    var count = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (grid[row][col] == xmas[0]) {
                for (direction in directions) {
                    if (findXmas(grid, row, col, 0, direction)) {
                        count++
                    }
                }
            }
        }
    }
    return count
}

fun findWord(
    grid: List<CharArray>,
    row: Int,
    col: Int,
    index: Int,
    word: String,
    direction: IntArray
): Boolean {
    if (row < 0 || col < 0 || row >= grid.size || col >= grid[0].size) return false
    if (grid[row][col] != word[index]) {
        return false
    }
    if (index == word.length - 1) {
        return true
    }
    val nextRow = row + direction[0]
    val nextCol = col + direction[1]
    return findWord(grid, nextRow, nextCol, index + 1, word, direction)
}

fun countXmasPart2(grid: List<CharArray>): Int {
    var count = 0
    val mas = "MAS"
    val sam = "SAM"
    for (row in grid.indices) {
        for (col in grid[0].indices) {
            if (grid[row][col] == 'M' || grid[row][col] == 'S') {
                val word = if (grid[row][col] == 'M') mas else sam

                if (findWord(grid, row, col, 0, word, directions[3])) {
                    if(grid[row][col + 2] == 'M' || grid[row][col + 2] == 'S') {
                        val secondWord = if (grid[row][col + 2] == 'M') mas else sam
                        if(findWord(grid, row, col + 2, 0, secondWord, directions[5])) {
                            count++
                        }
                    }
                }
            }
        }
    }
    return count
}

fun main() {
    fun part1(input: List<String>): Long {
        val inputAsArray = input.map { it.toCharArray() }
        return countXmas(inputAsArray).toLong()
    }

    fun part2(input: List<String>): Long {
        var result = 0L
        val inputAsArray = input.map { it.toCharArray() }
        return countXmasPart2(inputAsArray).toLong()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test", "Day04")
    part1(testInput).println()
    println("part 2 test: ${part2(testInput)}")
    check(part1(testInput) == 18L)
    check(part2(testInput) == 9L)

    val testInput2 = readInput("Day04_test", "Day04")
//    part2(testInput2).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04", "Day04")
    part1(input).println()
    println("part 2: ${part2(input)}")
}
