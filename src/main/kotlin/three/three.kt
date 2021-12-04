package three

import java.io.File
import java.lang.Math.pow

fun main() {
    val input = File("src/main/kotlin/three/input.txt").readLines()

    // part one
    val binaryGammaRate = calculateGammaRate_one(input)
    println(binaryGammaRate)
    val gammaDecimal = binaryStringToDecimal(binaryGammaRate).toInt()
    println(gammaDecimal)

    val binaryEpsilonRate = convertGammaToEpsilonRate_one(binaryGammaRate)
    println(binaryEpsilonRate)
    val epsilonDecimal = binaryStringToDecimal(binaryEpsilonRate).toInt()
    println(epsilonDecimal)

    println(gammaDecimal * epsilonDecimal)

    // part two
    var oxygenList = input
    var oxygenIndex = 0
    while (oxygenIndex < oxygenList[0].length) {
        oxygenList = findHigherWhatever(oxygenList, oxygenIndex)
        if (oxygenList.size == 1) {
            break
        }
        oxygenIndex += 1
    }
    println(oxygenList)
    val oxygen = binaryStringToDecimal(oxygenList.first())
    println(oxygen)

    var co2List = input
    var co2index = 0
    while (co2index < co2List[0].length) {
        co2List = findLowerWhatevers(co2List, co2index)
        if (co2List.size == 1) {
            break
        }
        co2index += 1
    }
    println(co2List)
    val co2 = binaryStringToDecimal(co2List.first())
    println(co2)

    println(oxygen * co2)
}

fun findHigherWhatever(input: List<String>, index: Int): List<String> {
    val binaryMap = getBinaryMap(input)

    val whichOne = if (binaryMap["0_$index"]!! > binaryMap["1_$index"]!!) "0" else "1"
    return input.filter { it.toCharArray()[index].toString() == whichOne }
}

fun findLowerWhatevers(input: List<String>, index: Int): List<String> {
    val binaryMap = getBinaryMap(input)

    val whichOne = if (binaryMap["0_$index"]!! > binaryMap["1_$index"]!!) "1" else "0"
    return input.filter { it.toCharArray()[index].toString() == whichOne }
}

fun calculateGammaRate_one(input: List<String>): String {
    var binaryGammaRate = ""

    val binaryMap = getBinaryMap(input)

    var index = 0
    while (index < input[0].length) {
        if (binaryMap["0_$index"]!! > binaryMap["1_$index"]!!) binaryGammaRate += "0" else binaryGammaRate += "1"
        index += 1
    }

    return binaryGammaRate
}

fun convertGammaToEpsilonRate_one(binaryGammaRate: String): String {
    return binaryGammaRate
        .replace("0", "a")
        .replace("1", "0")
        .replace("a", "1")
}

private fun getBinaryMap(input: List<String>): HashMap<String, Int> {
    var index = 0
    val binaryMap = hashMapOf<String, Int>()
    val lineLength = input[0].length
    while (index < lineLength) {
        binaryMap["0_$index"] = 0
        binaryMap["1_$index"] = 0
        index += 1
    }
    input.forEach { line ->
        line.toCharArray().forEachIndexed { charIndex, c ->
            if (c == '0') {
                binaryMap["0_$charIndex"] = binaryMap["0_$charIndex"]!! + 1
            } else {
                binaryMap["1_$charIndex"] = binaryMap["1_$charIndex"]!! + 1
            }
        }
    }
    return binaryMap
}

private fun binaryStringToDecimal(binary: String): Double {
    var decimal = 0.0
    binary.reversed().toCharArray().forEachIndexed { index, c ->
        decimal += pow(2.0, index.toDouble()) * c.toString().toInt()
    }
    return decimal
}
