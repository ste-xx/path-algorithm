import algorithm.BreathFirstSearch
import algorithm.DepthFirstSearch
import algorithm.Dijkstra
import algorithm.PathFindingAlgorithm
import board.*
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
                boardFactory = Board.Factory::angleLevelWithGoalOn_x13_y3WithMud,
                algorithms = listOf(BreathFirstSearch(),DepthFirstSearch(), Dijkstra()))
    }

}


