package algorithm

import board.Board
import tiles.Path

interface PathFindingAlgorithm {

    data class PathFindingResult(val solved: Boolean, val path: Path)

    suspend fun solveWithoutDelay(board: Board): PathFindingAlgorithm.PathFindingResult = solve(board, 0, 0)

    suspend fun solve(board: Board, initialWait: Int = 1000, drawWait: Int = 50): PathFindingAlgorithm.PathFindingResult

}