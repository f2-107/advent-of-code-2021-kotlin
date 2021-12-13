package seven

import java.io.File
import kotlin.math.abs

fun main() {
//    val input = File("src/main/kotlin/seven/test_input.txt").readLines()
    val input = File("src/main/kotlin/seven/input.txt").readLines()

    partOne(input)
    partTwo(input)
}

fun partTwo(input: List<String>) {
    val crabPositions = input[0].split(",").map { it.toInt() }.toList()
    println(crabPositions)
    val maxCrabPosition = crabPositions.maxOf { it }
    val minCrabPosition = crabPositions.minOf { it }

    val crabPositionMap = hashMapOf<Int, Int>()
    val usedFuelMap = hashMapOf<Int, Int>()
    (minCrabPosition..maxCrabPosition).forEach {
        crabPositionMap[it] = crabPositions.filter { pos -> pos == it }.count()
        usedFuelMap[it] = 0
    }

    println(crabPositionMap)
    println(usedFuelMap)

    usedFuelMap.forEach { position ->
        usedFuelMap[position.key] = crabPositionMap.map { entry ->
            if (entry.value > 0) {
                val positionChanges = abs(entry.key - position.key)
                val positionChangesCosts = (1..positionChanges).sumOf { it }
                return@map entry.value * (positionChangesCosts)
            }
            return@map 0
        }.sumOf { it }
    }

    println(usedFuelMap)
    val minFuel = usedFuelMap.minOf { entry -> entry.value }
    println("Min Fuel: $minFuel")
}

fun partOne(input: List<String>) {
    val crabPositions = input[0].split(",").map { it.toInt() }.toList()
    println(crabPositions)
    val maxCrabPosition = crabPositions.maxOf { it }
    val minCrabPosition = crabPositions.minOf { it }

    val crabPositionMap = hashMapOf<Int, Int>()
    val usedFuelMap = hashMapOf<Int, Int>()
    (minCrabPosition..maxCrabPosition).forEach {
        crabPositionMap[it] = crabPositions.filter { pos -> pos == it }.count()
        usedFuelMap[it] = 0
    }

    println(crabPositionMap)
    println(usedFuelMap)

    usedFuelMap.forEach { position ->
        usedFuelMap[position.key] = crabPositionMap.map { entry ->
            if (entry.value > 0) return@map entry.value * (abs(entry.key - position.key))
            return@map 0
        }.sumOf { it }
    }

    println(usedFuelMap)
    val minFuel = usedFuelMap.minOf { entry -> entry.value }
    println("Min Fuel: $minFuel")
}
