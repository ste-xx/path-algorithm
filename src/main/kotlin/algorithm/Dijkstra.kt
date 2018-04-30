package algorithm

import algorithm.Dijkstra.Distance.ConstDistances.UNKNOWN
import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import kotlinx.coroutines.experimental.delay
import tiles.Path
import tiles.Position
import tiles.Tile
import tiles.WallTile


operator fun Dijkstra.Distance.plus(other: Int) = Dijkstra.Distance(this.distance + other)
operator fun Int.plus(distance: Dijkstra.Distance) = Dijkstra.Distance(this + distance.distance)

class Dijkstra(override val name: String = "Dijkstra") : PathFindingAlgorithm {


    fun Board.BoardSize.countTiles() = this.width * this.height
    data class Distance(val distance: Int = 0,
                        private val isUnknown: Boolean = false,
                        private val isInfinite: Boolean = false) : Comparable<Distance> {
        override fun compareTo(other: Distance) = this.distance.compareTo(other.distance)

        companion object ConstDistances {
            val UNKNOWN = Distance(0, true, false)
            val INFINITE = Distance(-1, true, false)
        }
    }

    //Distance: NOT_VISITED, WITH_DISTANCE(Number), INFINITE
    data class Entry(val distance: Distance, val position: Position, val visited: Boolean = false, val parent: Entry? = null) : Iterable<Entry> {
        override operator fun iterator() = object : Iterator<Entry> {
            var entry = this@Entry
            override fun hasNext() = entry.parent != null
            override fun next(): Entry {
                val currentParent = entry.parent!!
                entry = currentParent
                return currentParent
            }
        }

        fun toPath() = this.fold(Path(this.position)) { path:Path, entry:Entry -> path+Path(entry.position) }

        companion object Factory {
            fun createNewEntryFrom(position: Position) = Entry(UNKNOWN, position, false, null)
        }
    }

    class DijsktraSet(set: Set<Position>) {
        private val positionToEntry: MutableMap<Position, Entry> = mutableMapOf(*set.map { Pair(it, Entry.createNewEntryFrom(it)) }.toTypedArray())
        val size = positionToEntry.size
        val unvisitedWithLowestDistance = object : Iterator<Entry> {
            override fun hasNext() = findUnvisitedEntryWithLowestKnownDistance() != null
            override fun next() = findUnvisitedEntryWithLowestKnownDistance()!!
        }

        operator fun iterator() = unvisitedWithLowestDistance


        fun printEntries(header: String) {
            println("--------$header---------")
            positionToEntry.forEach(::println)
        }

        fun getEntryOn(pos: Position) = this.positionToEntry[pos]!!

        fun updateStartPosition(position: Position): Entry {
            val entry = Entry(distance = Distance(0), position = position)
            positionToEntry[position] = entry
            return entry
        }

        fun updateFromUnvisitedToVisited(entry: Entry): Entry {
            val visitedEntry = entry.copy(visited = true)
            positionToEntry[entry.position] = visitedEntry
            return visitedEntry
        }

        fun updateFromUnvisitedToVisited(position: Position) = updateFromUnvisitedToVisited(getEntryOn(position))

        fun findUnvisitedEntryWithLowestKnownDistance() = positionToEntry.values
                .filter { !it.visited }
                .filterNot { it.distance == UNKNOWN }
                .minBy { it.distance }

        fun calculateCost(mayParent: Entry, current: Position, cost: Int = 1) {

            when {
                mayParent.position == current -> throw IllegalArgumentException("$current can not the potential parent of himself")
                positionToEntry[current] == null -> throw IllegalArgumentException("$current not found")
                !mayParent.visited -> throw IllegalArgumentException("Potential parent ($mayParent) must be visited.")
            }
            val currentEntry = positionToEntry[current]!!
            positionToEntry[current] = when {
                currentEntry.distance != UNKNOWN && currentEntry.distance < mayParent.distance + cost -> currentEntry
                else -> currentEntry.copy(distance = cost + mayParent.distance, parent = mayParent)
            }
        }
    }

    override suspend fun solve(board: Board, drawWait: Int): PathFindingResult {
        val dijsktraSet = DijsktraSet(board.getAllTiles()
                .map(Tile::position)
                .toMutableSet())

        dijsktraSet.updateStartPosition(board.findStart().position)
        //while unvisted node exists
        val goalPosition = board.findGoal().position
        //todo implements iterable -> reduce
        for (unvisited in dijsktraSet.unvisitedWithLowestDistance) {
            val visitedEntry = dijsktraSet.updateFromUnvisitedToVisited(unvisited)
            val neighbourTiles = board.getNeighboursOn(visitedEntry.position)
            delay(drawWait)
            for (tile in neighbourTiles) {
                when{
                    !board.boardSize().isInBoard(tile.position)-> "noop"
                    tile is WallTile -> dijsktraSet.updateFromUnvisitedToVisited(tile.position)
                    else -> dijsktraSet.calculateCost(visitedEntry, tile.position)
                }
            }
            if(visitedEntry.position == goalPosition){
                break;
            }
        }

        val result = dijsktraSet.getEntryOn(goalPosition)
        return when{
            result.parent == null -> PathFindingResult(false, Path.EMPTY_PATH)
            else -> {
                val resultPath = result.toPath()
                board.setPath(resultPath)
                PathFindingResult(true, resultPath)
            }
        }
    }

}
