package five

import java.io.File
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan

fun main() {
//    val input = File("src/main/kotlin/five/test_input.txt").readLines()
    val input = File("src/main/kotlin/five/input.txt").readLines()
    val gridSize = 999

    partOne(input, gridSize)
    partTwo(input, gridSize)
}

private fun partTwo(input: List<String>, gridSize: Int) {
    println("part one")
    val grid = Grid(gridSize)

    grid.printGrid()

    input.forEachIndexed { index, line ->
        val (coordOne, coordTwo) = line.split("->")
        val (x1, y1) = coordOne.trim().split(",").map { it.toInt() }
        val (x2, y2) = coordTwo.trim().split(",").map { it.toInt() }

        if (x1 == x2 || y1 == y2) {
            if (x1 == x2) {
                val range = if (y1 > y2) (y2..y1) else (y1..y2)
                range.forEach { grid.updateGrid(it, x1) }
            }
            if (y1 == y2) {
                val range = if (x1 > x2) (x2..x1) else (x1..x2)
                range.forEach { grid.updateGrid(y1, it) }
            }
        }

        val degrees = ( atan((y2 - y1).toDouble() / (x2 - x1).toDouble()) * 180 / PI).toInt()
        if (abs(degrees) == 45) {
            if (degrees < 0) {
                val xRange = if (x2 > x1) (x1..x2).toList() else (x2..x1).toList()
                val yRange = if (y2 > y1) (y1..y2).toList() else (y2..y1).toList()
                yRange.forEachIndexed { index, y ->
                    grid.updateGrid(y, xRange.reversed()[index])
                }
            }
            if (degrees >= 0) {
                val xRange = if (x2 > x1) (x1..x2).toList() else (x2..x1).toList()
                val yRange = if (y2 > y1) (y1..y2).toList() else (y2..y1).toList()
                yRange.forEachIndexed { index, y ->
                    grid.updateGrid(y, xRange[index])
                }
            }
        }
    }

    grid.printGrid()
    println("Overlapping count: ${grid.countOverlapping()}")
}

private fun partOne(input: List<String>, gridSize: Int) {
    println("part one")
    val grid = Grid(gridSize)

    grid.printGrid()

    input.forEachIndexed { index, line ->
        val (coordOne, coordTwo) = line.split("->")
        val (x1, y1) = coordOne.trim().split(",").map { it.toInt() }
        val (x2, y2) = coordTwo.trim().split(",").map { it.toInt() }

        if (x1 == x2 || y1 == y2) {
            if (x1 == x2) {
                val range = if (y1 > y2) (y2..y1) else (y1..y2)
                range.forEach { grid.updateGrid(it, x1) }
            }
            if (y1 == y2) {
                val range = if (x1 > x2) (x2..x1) else (x1..x2)
                range.forEach { grid.updateGrid(y1, it) }
            }
        }

    }

    grid.printGrid()
    println("Overlapping count: ${grid.countOverlapping()}")
}

class Grid(maxGrid: Int) {
    private val matrix: Array<Array<Int>>
    private val gridSize: Int

    init {
        gridSize = maxGrid
        matrix = (0..gridSize).map { (0..gridSize).map { 0 }.toTypedArray() }.toTypedArray()
    }

    fun printGrid() {
        for (y in 0..gridSize) {
            print("$y: ")
            for (x in 0..gridSize) {
                if (matrix[y][x] == 0) print(".") else print(matrix[y][x])
            }
            println()
        }
    }

    fun updateGrid(y: Int, x: Int) {
        matrix[y][x] += 1
    }

    fun countOverlapping(): Int {
        var count = 0
        matrix.forEach { y ->
            y.forEach { x ->
                if (x > 1) count += 1
            }
        }
        return count
    }
}
