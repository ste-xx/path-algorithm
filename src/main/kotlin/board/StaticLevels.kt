package board

import tiles.EmptyTile
import tiles.GoalTile
import tiles.StartTile
import tiles.WallTile

fun Board.Factory.angleLevelWithGoalOn_x13_y3(ui: BoardUi): Board = Board.create(ui) {
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

fun Board.Factory.weirdLevel(ui: BoardUi): Board = Board.create(ui) {
    Array(15) { x ->
        Array(10) { y ->
            when {
                (x == 5) and (y == 0) -> GoalTile(x, y)
                (x == 6) and (y in 0..1) -> WallTile(x, y)
                (x == 10) and (y in 1..2) -> WallTile(x, y)
                (x in 0..2 || x in 4..10 || x in 12..15) and (y == 3) -> WallTile(x, y)
                (x in 0..1 || x in 3..15) and (y == 5) -> WallTile(x, y)
                (x in 0..10 || x in 12..15) and (y == 7) -> WallTile(x, y)
                (x == 2) and (y == 9) -> StartTile(x, y)
                else -> EmptyTile(x, y)
            }
        }
    }
}