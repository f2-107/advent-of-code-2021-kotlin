package four

import java.io.File

fun main() {
//    val input = File("src/main/kotlin/four/test_input.txt").readLines()
    val input = File("src/main/kotlin/four/input.txt").readLines()

    val filterOutEmptyLines = input.filter { line -> line.isNotEmpty() }
    val pickNumbers = input[0].split(",")

    println(pickNumbers)
    val boardsRawInfo = filterOutEmptyLines.subList(1, filterOutEmptyLines.size)
    println(boardsRawInfo)

    println(pickNumbers)
    println(boardsRawInfo)

    val boardOne = generateBingoBoards(boardsRawInfo)
    partOne(pickNumbers, boardOne)

    val boardTwo = generateBingoBoards(boardsRawInfo)
    partTwo(pickNumbers, boardTwo)
}

// part two
fun partTwo(pickNumbers: List<String>, boards: List<Board>) {
    val partTwoBoards = boards.toMutableList()
    var partTwoPickNumbers = pickNumbers.toMutableList()
    val winnerDataList = arrayListOf<WinnerData>()
    while (winnerDataList.size < boards.size) {
        val winnerData = playBingoAndGetWinnerBoard(partTwoPickNumbers, partTwoBoards)
        winnerDataList.add(winnerData)
        partTwoBoards.remove(winnerData.board)
    }
    println(winnerDataList)

    println("--- LAST WINNER ---")
    println("WinnerBoard: ${winnerDataList.last().board}, WinnerNumber: ${winnerDataList.last().pickedNumber}")

    val sumRemainingNumbers = calcBoard(winnerDataList.last().board)
    println("Multiply of remaining numbers [$sumRemainingNumbers] x winning number [${winnerDataList.last().pickedNumber}] = ${sumRemainingNumbers * winnerDataList.last().pickedNumber.toInt()}")}

// part one
fun partOne(pickNumbers: List<String>, boards: List<Board>) {
    val partOneBoards = boards.toMutableList()
    val winnerData =
        playBingoAndGetWinnerBoard(pickNumbers, partOneBoards)
    if (winnerData.board.rows.isEmpty() && winnerData.pickedNumber == "0") {
        println("something wrong: $winnerData")
        return
    }
    println("---WINNER---")
    println("WinnerBoard: ${winnerData.board}, WinnerNumber: ${winnerData.pickedNumber}")

    val sumRemainingNumbers = calcBoard(winnerData.board)
    println("Multiply of remaining numbers [$sumRemainingNumbers] x winning number [${winnerData.pickedNumber}] = ${sumRemainingNumbers * winnerData.pickedNumber.toInt()}")
}

fun playBingoAndGetWinnerBoard(
    pickedNumbers: List<String>,
    boards: MutableList<Board>
): WinnerData {
    boards.forEach { println(it) }
    println("--- LOS")
    pickedNumbers.forEach { number ->
        println("--- Picked Number: $number ---")
        // mark picked number
        boards.forEachIndexed { boardIndex, board ->
            board.rows.forEachIndexed { rowIndex, row ->
                board.rows[rowIndex] = row.map { v -> if (v == number) "x" else v }.toMutableList()
                println("boardindex: $boardIndex, index: $rowIndex, row: $row")
            }
            println(board)

            // check winner columns horizontal
            board.rows.forEachIndexed { rowIndex, row ->
                if (row.all { it == "x" }) {
                    println("Board $boardIndex has horizontal winner line")
                    return WinnerData(board, number)
                }
            }
            // check winner column
            var index = 0
            while (index < board.rows.size) {
                var secondIndex = 0
                var countColumnX = 0
                while (secondIndex < board.rows.size) {
                    if (board.rows[secondIndex][index] == "x") countColumnX += 1
                    secondIndex += 1
                }
                if (countColumnX == 5) return WinnerData(board, number)
                index += 1
            }

        }
        boards.forEach { println(it) }
    }
    return WinnerData(Board(mutableListOf()), "0")
}


private fun calcBoard(
    board: Board
): Int {
    var sumRemainingNumbers = 0
    board.rows.forEach { row ->
        row.forEach { value ->
            if (value != "x") sumRemainingNumbers += value.toInt()
        }
    }
    return sumRemainingNumbers
}

private fun generateBingoBoards(boardsRawInfo: List<String>): List<Board> {
    var generateBoards = mutableListOf<Board>()
    var boardIndex = 0
    var boardsCount = 0

    while (boardIndex < boardsRawInfo.size) {
        var rows = mutableListOf<MutableList<String>>()
        boardsRawInfo.subList(boardIndex, boardIndex + 5).forEachIndexed { rowIndex, rowValues ->
            rows.add(rowIndex, rowValues.trimStart().split("\\s+".toRegex()).toMutableList())
        }
        generateBoards.add(boardsCount, Board(rows))
        boardIndex += 5
        boardsCount += 1
    }
    return generateBoards.toList()
}

data class Board(val rows: MutableList<MutableList<String>>)
data class WinnerData(val board: Board, val pickedNumber: String)
