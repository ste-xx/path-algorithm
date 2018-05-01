package algorithm

import algorithm.BreathFirstSearch.Entry.Factory.NOT_FOUND
import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import kotlinx.coroutines.experimental.delay
import tiles.*

class BreathFirstSearch(override val name: String = "BFS") : PathFindingAlgorithm {

    data class Entry(val position: Position, val previous: Entry?) {

        companion object Factory {
            val NOT_FOUND = Entry(Position(-1, -1), null)
        }

        fun collectPath(): Path = when (previous) {
            is Entry -> Path(position) + previous.collectPath()
            else -> Path(position)
        }

    }

    override suspend fun solve(board: Board, drawWait: Int): PathFindingResult {

        val startTile: Tile = board.findStart()
        val rest = Queue(Entry(startTile.position, null))

        var searchResult: Entry = NOT_FOUND
        while (rest.isNotEmpty()) {
            val entry = rest.dequeue()!!
            val currentTile = board.getTileOn(entry.position)

            delay(drawWait)

            if (currentTile is GoalTile) {
                searchResult = entry
                break
            }

            if (currentTile is VisitedTile) {
                println("Should never revisited a tile $currentTile.position2")
                continue
            }


            val movableNeighbours = board.getNeighboursOn(entry.position).toList()
                    .filter { (it !is VisitedTile) && (it is GoalTile || it is WalkableTile) }
                    .filterNot { rest.items.map { it.position }.contains(it.position) } //fuck, better track locally


            rest.enqueue(movableNeighbours.map { Entry(it.position, entry) })
        }

        return when (searchResult) {
            NOT_FOUND -> PathFindingResult(false, Path())
            else -> {
                board.setPath(searchResult.collectPath())
                PathFindingResult(true, searchResult.collectPath())
            }

        }
    }


}