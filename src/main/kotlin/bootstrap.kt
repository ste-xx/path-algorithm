import algorithm.BreathFirstSearch
import algorithm.PathFindingAlgorithm

import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import board.angleLevelWithGoalOn_x13_y3
import kotlinx.coroutines.experimental.launch
import board.BoardUi
import board.RoughJsBoard
import tiles.LegendUi
import tiles.RoughJsLegend


object Bootstrap {

    fun bootstrap(boardUi: BoardUi,
                  legendUi: LegendUi,
                  boardFactory: (BoardUi) -> Board,
                  algorithm: PathFindingAlgorithm) {
        launch {
            legendUi.draw()
            val board = boardFactory(boardUi)
            algorithm.solve(board)
        }
    }

    fun bootstrapJS() {
        bootstrap(boardUi = RoughJsBoard,
                legendUi = RoughJsLegend,
                boardFactory = Board.Factory::angleLevelWithGoalOn_x13_y3,
                algorithm = BreathFirstSearch())
    }

}