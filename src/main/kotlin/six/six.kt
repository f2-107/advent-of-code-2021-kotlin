package six

import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
//    val input = File("src/main/kotlin/six/test_input.txt").readLines()
    val input = File("src/main/kotlin/six/input.txt").readLines()

    val startLanternfishs = input[0].split(",").map { it.toInt() }.toList()
    partOne(startLanternfishs, 80)
    partTwo(startLanternfishs, 256)
}

fun partOne(startLanternfishs: List<Int>, days: Int) {
    println("Start List: $startLanternfishs")

    val mutableLanternfishList = startLanternfishs.toMutableList()
    for (day in 1..days) {
        var countNewLanternfishs = 0
        mutableLanternfishList.forEachIndexed { index, lanternfish ->
            when (lanternfish) {
                0 -> {
                    countNewLanternfishs += 1
                    mutableLanternfishList[index] = 6
                }
                in 1..8 -> {
                    mutableLanternfishList[index] = lanternfish - 1
                }
            }
        }
        (1..countNewLanternfishs).forEach {
            mutableLanternfishList.add(8)
        }
        println("After day $day: ${mutableLanternfishList.size} lanternfishs")
    }
    println("Total lanternfishs: ${mutableLanternfishList.size}")
}

fun partTwo(startLanternfishs: List<Int>, days: Int) {
    println("Start List: $startLanternfishs")

    val lanternfishList = arrayListOf<Long>()
    (0..8).forEach { lanternfishList.add(0) }

    println(lanternfishList)
    startLanternfishs.forEach { lanternfishList[it] = lanternfishList[it].plus(1) }

    println(lanternfishList)
    val neededTime = measureTimeMillis {
        (1..days).forEach { day ->
            var zeros = 0L
            lanternfishList.forEachIndexed { index, _ ->
                when(index) {
                    0 -> {
                        zeros = lanternfishList[index]
                        lanternfishList[index] = 0
                    }
                    in 1..8 -> {
                        lanternfishList[index - 1] += lanternfishList[index]
                        lanternfishList[index] = 0
                    }
                }
            }
            lanternfishList[6] += zeros
            lanternfishList[8] = zeros
            println("After day $day: ${lanternfishList.sum()} lanternfishs, lanternfishList[$lanternfishList]")
        }
    }
    println("Total lanternfish: ${lanternfishList.sum()} lanternfish - time: ${neededTime}ms")
    println("Total lanternfish: 26984457539 test lanternfish")
}
