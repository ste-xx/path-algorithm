package level

import board.Board
import tiles.Position
import tiles.*
import board.ConsoleBoard

fun Board.Factory.angleLevelWithGoalOn_x11_y1(): Board = Board.create(ConsoleBoard) {
    Array(15) { x ->
        Array(12) { y ->
            when {
                (x == 11) and (y == 1) -> GoalTile(x, y)
                (x in 3..12) and (y == 3) -> WallTile(x, y)
                (x == 12) and (y in 3..9) -> WallTile(x, y)
                (x == 2) and (y == 11) -> StartTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}

fun Board.Factory.angleLevelWithGoalOn_x13_y3(): Board = Board.create(ConsoleBoard) {
    Array(15) { x ->
        Array(12) { y ->
            when {
                (x == 13) and (y == 3) -> GoalTile(x, y)
                (x in 3..12) and (y == 3) -> WallTile(x, y)
                (x == 12) and (y in 3..9) -> WallTile(x, y)
                (x == 2) and (y == 11) -> StartTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}

fun shortestPathForAngleLevelWithGoalOn_x13_y3(withStartTile: Boolean=true): List<Position> {
    val startTile = if(withStartTile) listOf(Position(2, 11)) else listOf()
    val bottomLine = (2..12).map { Position(it, 10) }
    val topLine = (10 downTo 3).map { Position(13, it) }

    return startTile + bottomLine + topLine
}


fun Board.Factory.createWithConsoleUi(supplier: () -> Array<Array<Tile>>) = Board.create(ConsoleBoard, supplier)
fun Board.Factory.createEmptyWithConsoleUi(boardSize: Board.BoardSize) = Board.createEmpty(boardSize, ConsoleBoard)

fun Board.Factory.neighbourLevel(): Board = Board.create(ConsoleBoard) {
    Array(3) { x ->
        Array(3) { y ->
            when {
                (x == 1) and (y == 0) -> EmptyTile(x, y)
                (x == 2) and (y == 1) -> GoalTile(x, y)
                (x == 1) and (y == 2) -> StartTile(x, y)
                (x == 0) and (y == 1) -> WallTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}

fun Board.Factory.wallLevel(): Board = Board.create(ConsoleBoard) {
    Array(3) { x ->
        Array(3) { y ->
            when {
                (x == 0) and (y == 0) -> StartTile(x, y)
                (x == 2) and (y == 0) -> GoalTile(x, y)
                (x in 0..2) and (y == 1) -> WallTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}

fun Board.Factory.unsolvableWallLevel(): Board = Board.create(ConsoleBoard) {
    Array(3) { x ->
        Array(3) { y ->
            when {
                (x == 0) and (y == 0) -> StartTile(x, y)
                (x == 0) and (y == 2) -> GoalTile(x, y)
                (x in 0..2) and (y == 1) -> WallTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}