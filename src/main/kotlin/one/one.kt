package one

import java.io.File

fun main() {
    val input = File("src/main/kotlin/one/input.txt").readLines().map { it.toInt() }

    countIncreases(input) //part one
    countSlides(input) //part two
}

private fun countSlides(input: List<Int>) {
    var increases = 0
    input.forEachIndexed { index, i ->
        if (index < input.size - 3) {
            val currWindow = input[index] + input[index + 1] + input[index + 2]
            val nextWindow = input[index + 1] + input[index + 2] + input[index + 3]
            if (nextWindow > currWindow) increases += 1
        }
    }

    println("Total slide increases: $increases")
}

private fun countIncreases(input: List<Int>) {
    var increases = 0
    input.forEachIndexed { index, i -> if (index != 0 && input[index] > input[index - 1]) increases += 1 }

    println("Total increases: $increases")
}

