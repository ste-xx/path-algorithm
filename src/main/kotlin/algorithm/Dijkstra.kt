package algorithm

import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import tiles.Path

class Dijkstra(override val name: String = "Dijkstra") : PathFindingAlgorithm {

    override suspend fun solve(board: Board, drawWait: Int): PathFindingResult {

        return PathFindingResult(false, Path())
    }

}