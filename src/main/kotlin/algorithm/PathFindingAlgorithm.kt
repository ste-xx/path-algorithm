package algorithm

import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import tiles.Path

interface PathFindingAlgorithm {

    data class PathFindingResult(val solved: Boolean, val path: Path)

    suspend fun solveWithoutDelay(board: Board) = solve(board, 0)

    suspend fun solve(board: Board, drawWait: Int = 50): PathFindingResult

    val name:String
}