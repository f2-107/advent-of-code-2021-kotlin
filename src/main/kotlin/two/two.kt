package two

import java.io.File

fun main() {
    val input = File("src/main/kotlin/two/input.txt").readLines()

    multiplyHorizontalAndDepth(input)
    multiplyHorizontalAndDepthWithAim(input)
}

private fun multiplyHorizontalAndDepthWithAim(input: List<String>) {
    var horizontal = 0
    var aim = 0
    var depth = 0
    input.forEach { line ->
        val (command, value) = line.split(" ")
        when (command) {
            "forward" -> {
                horizontal += value.toInt()
                depth += value.toInt() * aim
            }
            "down" -> aim += value.toInt()
            "up" -> aim -= value.toInt()
        }
    }

    println("Horizontal[$horizontal], Depth[$depth], multiply[${horizontal * depth}]")
}

private fun multiplyHorizontalAndDepth(input: List<String>) {
    var horizontal = 0
    var depth = 0
    input.forEach { line ->
        val (command, value) = line.split(" ")
        when (command) {
            "forward" -> horizontal += value.toInt()
            "down" -> depth += value.toInt()
            "up" -> depth -= value.toInt()
        }
    }

    println("Horizontal[$horizontal], Depth[$depth], multiply[${horizontal * depth}]")
}
