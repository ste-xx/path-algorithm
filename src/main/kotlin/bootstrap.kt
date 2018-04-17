import algorithm.BreathFirstSearch
import algorithm.Dijkstra
import algorithm.PathFindingAlgorithm
import board.Board
import board.BoardUi
import board.RoughJsBoard
import board.angleLevelWithGoalOn_x13_y3
import kotlinx.coroutines.experimental.launch
import tiles.LegendUi
import tiles.RoughJsLegend



const val FOOTER_AND_HEADER_SIZE = 200

object Bootstrap {

    fun bootstrap(controlUi: ControlUi,
                  boardUi: BoardUi,
                  legendUi: LegendUi,
                  boardFactory: (BoardUi) -> Board,
                  algorithms: List<PathFindingAlgorithm>) {
        launch {
            val board = boardFactory(boardUi)
            legendUi.draw()
            controlUi.draw(board = board, algorithms = algorithms)
        }
    }

    fun bootstrapJS() {
        bootstrap(
                controlUi = RoughJsControl,
                boardUi = RoughJsBoard,
                legendUi = RoughJsLegend,
                boardFactory = Board.Factory::angleLevelWithGoalOn_x13_y3,
                algorithms = listOf(BreathFirstSearch(), Dijkstra()))
    }

}


