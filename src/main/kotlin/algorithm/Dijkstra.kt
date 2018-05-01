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

    data class Distance(val distance: Int = 0,
                        private val isUnknown: Boolean = false,
                        private val isInfinite: Boolean = false) : Comparable<Distance> {
        override fun compareTo(other: Distance) = this.distance.compareTo(other.distance)

        companion object ConstDistances {
            val UNKNOWN = Distance(0, true, false)
        }
    }

    data class Entry(val distance: Distance, val node: Position, val visited: Boolean = false, val parent: Entry? = null) : Iterable<Entry> {
        /**
         * Iterates though the parents
         */
        override operator fun iterator() = object : Iterator<Entry> {
            var entry = this@Entry
            override fun hasNext() = entry.parent != null
            override fun next(): Entry {
                val currentParent = entry.parent!!
                entry = currentParent
                return currentParent
            }
        }

        /**
         * Get the path from this node to all parent nodes
         */
        fun toPath() = this.fold(Path(this.node)) { path, entry -> path + Path(entry.node) }

        companion object Factory {
            fun createNewEntryFrom(position: Position) = Entry(UNKNOWN, position, false, null)
        }
    }

    /**
     * Can iterate over unvisited nodes. Sets them automatically to visited.
     */
    class DijsktraSet(nodes: Set<Position>, startNode: Position, val goalNode: Position? = null) : Iterable<Entry> {
        internal val positionToEntry: MutableMap<Position, Entry>
        private val iterator: Iterator<Entry>

        private var solved = false
        fun isSolved() = solved

        init {
            positionToEntry = mutableMapOf(*nodes.map { Pair(it, Entry.createNewEntryFrom(it)) }.toTypedArray())
            positionToEntry[startNode] = Entry(distance = Distance(0), node = startNode)

            iterator = when {
                goalNode != null -> object : Iterator<Entry> {
                    private var previous: Entry? = null
                    override fun hasNext() = when {
                        previous?.node == goalNode -> {
                            solved = true
                            false
                        }
                        findUnvisitedEntryWithLowestKnownDistance() == null -> false
                        else -> true
                    }

                    override fun next(): Entry {
                        previous = updateEntryToVisited(findUnvisitedEntryWithLowestKnownDistance()!!)
                        return previous!!
                    }
                }
                else -> object : Iterator<Entry> {
                    override fun hasNext() = findUnvisitedEntryWithLowestKnownDistance() != null
                    override fun next() = updateEntryToVisited(findUnvisitedEntryWithLowestKnownDistance()!!)
                }
            }
        }

        override operator fun iterator() = iterator

        fun getEntryOn(pos: Position) = this.positionToEntry[pos]!!

        internal fun updateEntryToVisited(entry: Entry): Entry {
            val visitedEntry = entry.copy(visited = true)
            positionToEntry[entry.node] = visitedEntry
            return visitedEntry
        }

        fun updateEntryToVisited(position: Position) = updateEntryToVisited(getEntryOn(position))

        //      really expensive!
        fun findUnvisitedEntryWithLowestKnownDistance() = positionToEntry.values
                .filter { !it.visited }
                .filter { it.distance != UNKNOWN }
                .minBy { it.distance }

        fun calculateCost(mayParent: Entry, current: Position, cost: Int = 1) {

            when {
                mayParent.node == current -> throw IllegalArgumentException("$current can not the potential parent of himself")
                positionToEntry[current] == null -> throw IllegalArgumentException("$current not found")
                !mayParent.visited -> throw IllegalArgumentException("Potential parent ($mayParent) must be visited.")
            }
            val currentEntry = positionToEntry[current]!!
            positionToEntry[current] = when {
                currentEntry.distance != UNKNOWN && currentEntry.distance < mayParent.distance + cost -> currentEntry
                else -> currentEntry.copy(distance = cost + mayParent.distance, parent = mayParent)
            }
        }

        fun buildPathFromStartToGoal() = when {
            !solved -> throw IllegalStateException("Set is not solved")
            goalNode == null -> throw IllegalStateException("No goal defined")
            else -> getEntryOn(goalNode).toPath()
        }
    }

    override suspend fun solve(board: Board, drawWait: Int): PathFindingResult {
        val set = DijsktraSet(board.getAllTiles().map(Tile::position).toSet(),
                board.findStart().position,
                board.findGoal().position)


        for (newVisited in set) {
            val neighbourTiles = board.getNeighboursOn(newVisited.node)
            delay(drawWait)
            neighbourTiles
                    .filter { board.boardSize().isInBoard(it.position) }
                    .forEach {
                        when (it) {
                            is WallTile -> set.updateEntryToVisited(it.position)
                            else -> set.calculateCost(newVisited, it.position)
                        }
                    }
        }

        return when {
            set.isSolved() -> {
                val resultPath = set.buildPathFromStartToGoal()
                board.setPath(set.buildPathFromStartToGoal())
                PathFindingResult(true, resultPath)
            }
            else -> PathFindingResult(false, Path.EMPTY_PATH)
        }
    }

}
