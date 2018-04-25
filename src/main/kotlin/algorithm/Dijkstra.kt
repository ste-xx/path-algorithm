package algorithm

import algorithm.Dijkstra.Distance.ConstDistances.UNKNOWN
import algorithm.PathFindingAlgorithm.PathFindingResult
import board.Board
import tiles.Path
import tiles.Position
import tiles.Tile


operator fun Dijkstra.Distance.plus(other: Int) = Dijkstra.Distance(this.distance + other)
operator fun Int.plus(distance: Dijkstra.Distance) = Dijkstra.Distance(this + distance.distance)

class Dijkstra(override val name: String = "Dijkstra") : PathFindingAlgorithm {


    fun Board.BoardSize.countTiles() = this.width * this.height
    data class Distance(val distance: Int = 0) : Comparable<Distance> {
        override fun compareTo(other: Distance) = this.distance.compareTo(other.distance)
        companion object ConstDistances {
            val UNKNOWN = Distance(0)
            val INFINITE = Distance(-1)
        }
    }

    //Distance: NOT_VISITED, WITH_DISTANCE(Number), INFINITE
    data class Entry(val distance: Distance, val parent: Entry?, val position: Position, val visited: Boolean = false) {
        companion object Factory {
            fun createNewEntryFrom(position: Position) = Entry(UNKNOWN, null, position, false)
        }
    }

    class DijsktraSet(set: MutableSet<Position>) {
        private val positionToEntry: MutableMap<Position, Entry> = mutableMapOf(*set.map { Pair(it, Entry.createNewEntryFrom(it)) }.toTypedArray())
        private val iter: Iterator<Dijkstra.Entry>
        val size = positionToEntry.size

        class IteratorUnvisitedWithLowestDistance(val set: DijsktraSet) : Iterator<Dijkstra.Entry> {
            override fun hasNext() = set.findUnvisitedEntryWithLowestDistance() != null
            override fun next() = set.findUnvisitedEntryWithLowestDistance()!!
        }

        init {
            this.iter = IteratorUnvisitedWithLowestDistance(this)
        }

        operator fun iterator(): Iterator<Dijkstra.Entry> = iter

        fun printEntries(header:String){
            println("--------$header---------")
            positionToEntry.forEach(::println)
            println("------------------------")
        }

        fun updateStartPosition(position: Position) {
            positionToEntry[position] = Entry(Distance(0), null, position, false)
        }

        fun updateFromUnvisitedToVisited(entry: Entry) {
            //validate -> entry with this parent, what happend ?
            positionToEntry[entry.position] = entry.copy(visited = true)
        }

        fun findUnvisitedEntryWithLowestDistance() = positionToEntry.values
                .filter { !it.visited }
                .minBy { it.distance }

        fun updateFrom(neighbourTiles: Board.NeighbourTiles) {
            //get directly o(1), better use hashmap? position -> entry
//            val e = Entry(distance = Distance(1), parent = null, position = Position(1,1))
//            val startEntry = set.indexOf(e)

            //{it.position == neighbourTiles.top.position}!!

        }


        fun calculateCost(mayParent: Entry, current: Position) {
//            var cost = positionToEntry[mayParent.position]!!.distance;
//            val node = positionToEntry[mayParent.position];
////
//            while (node = node.parent) {
//                cost += node.distance.distance
//            }

            //update position cost --> set parent

            val currentEntry = positionToEntry[current]!!
            //sum is distance or hold distance in current node?
            //this cost < as updatePositionCost -> update other do nothing
            //import extension method?


            positionToEntry[current] = currentEntry.copy(distance = 1 + mayParent.distance, parent = mayParent)
        }
    }

    override suspend fun solve(board: Board, drawWait: Int): PathFindingResult {
        val dijsktraSet = DijsktraSet(board.getAllTiles()
                .map(Tile::position)
                .toMutableSet())

        dijsktraSet.updateStartPosition(board.findStart().position)
        //while unvisted node exists

        for (unvisited in dijsktraSet) {
            dijsktraSet.updateFromUnvisitedToVisited(unvisited)
            val neighbourTiles = board.getNeighboursOn(unvisited.position);
            //calculate cost
            dijsktraSet.calculateCost(unvisited, neighbourTiles.top.position)
        }

        println(dijsktraSet.findUnvisitedEntryWithLowestDistance()) //&&NOT_VISITED

        return PathFindingResult(false, Path())
    }

}
