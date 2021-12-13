package eight

import java.io.File

fun main() {
    val input = File("src/main/kotlin/eight/test_input.txt").readLines()
//    val input = File("src/main/kotlin/eight/input.txt").readLines()

    val numbers = hashMapOf<Int, String>()
    numbers[0] = "abcefg"
    numbers[1] = "cf"
    numbers[2] = "acdeg"
    numbers[3] = "acdfg"
    numbers[4] = "bcdf"
    numbers[5] = "abdfg"
    numbers[6] = "abdefg"
    numbers[7] = "acf"
    numbers[8] = "abcdefg"
    numbers[9] = "abcdfg"


}
