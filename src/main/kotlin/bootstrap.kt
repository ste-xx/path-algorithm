import algorithm.BreathFirstSearch
import board.Board
import board.angleLevelWithGoalOn_x13_y3
import kotlinx.coroutines.experimental.launch
import ui.BoardUi
import ui.RoughJsUi


object Bootstrap {

    fun bootstrap(ui: BoardUi) {
        launch {
            val board = Board.angleLevelWithGoalOn_x13_y3(ui)
            BreathFirstSearch().solve(board)
        }
    }

    fun bootstrapJS() {
        bootstrap(RoughJsUi)
    }

}