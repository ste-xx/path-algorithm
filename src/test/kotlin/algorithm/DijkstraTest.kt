package algorithm

import algorithm.Dijkstra.*
import algorithm.Dijkstra.Distance.ConstDistances.INFINITE
import algorithm.Dijkstra.Distance.ConstDistances.UNKNOWN
import board.Board
import level.*
import testutil.runAsyncTest
import tiles.Path
import tiles.Position
import kotlin.test.*

class DijkstraTest {

    private val pos00 = Position(0, 0)
    private val pos01 = Position(0, 1)
    private val pos02 = Position(0, 2)
    private val pos03 = Position(0, 3)

    @Test
    fun check_distance_equality() {
        assertFalse(Distance(0) == UNKNOWN, "UNKNOWN is not Distance(0)")
        assertFalse(Distance(-1) == INFINITE, "INFINITE is not Distance(-1)")
        assertFalse(UNKNOWN == INFINITE, "UNKNOWN is not INFINITE")

        assertTrue(Distance(0) == Distance(0), "Distance(0) is Distance(0)")
        assertFalse(Distance(1) == Distance(0), "Distance(1) is not Distance(0)")
    }

    @Test
    fun dijkstra_entries_iterator() {
        val entry = Entry(distance = Distance(2), position = pos02,
                parent = Entry(distance = Distance(1), position = pos01,
                        parent = Entry(distance = Distance(0), position = pos00)))
        for(e in entry){ }
        assertTrue(entry.iterator().hasNext())
    }

    @Test
    fun dijkstra_entries_to_path() {
        val entry = Entry(distance = Distance(2), position = pos02,
                parent = Entry(distance = Distance(1), position = pos01,
                        parent = Entry(distance = Distance(0), position = pos00)))

        val expected = Path(pos00)+Path(pos01)+Path(pos02)
        assertEquals(expected,entry.toPath())
    }

    @Test
    fun should_throw_an_error_when_calculate_cost_will_be_called_with_an_unvisted_node() {
        val set = DijsktraSet(setOf(pos00, pos01, pos02))
        val startEntry = set.updateStartPosition(pos00)
        assertFailsWith(IllegalArgumentException::class) {
            set.calculateCost(startEntry, pos01)
        }
    }

    @Test
    fun should_not_find_unvisted_entry_with_lowest_distance_if_no_start_position_exists() {
        val set = DijsktraSet(setOf(pos00, pos01, pos02))
        assertNull(set.findUnvisitedEntryWithLowestKnownDistance())
    }

    @Suppress("ConstantConditionIf")
    @Test
    fun should_get_shortest_path_with_manual_execution() {
        val withDebug = false
//        (0,0) <-> (0,1) -> cost 1
//        (0,1) <-> (0,2) -> cost 1
//        (0,0) <-> (0,2) -> cost 3
//        (0,2) <-> (0,3) -> cost 1
        val set = DijsktraSet(setOf(pos00, pos01, pos02, pos03))
        if (withDebug) set.printEntries("init")
        val startEntry = set.updateStartPosition(pos00)
        if (withDebug) set.printEntries("update Start")

        val visitedStartEntry = set.updateFromUnvisitedToVisited(startEntry)
        if (withDebug) set.printEntries("update from unvisited to visited")
        set.calculateCost(visitedStartEntry, pos01)
        set.calculateCost(visitedStartEntry, pos02, 3)
        if (withDebug) set.printEntries("calc cost from $startEntry to $pos01 and $pos02")

        val e01 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(pos01, e01.position)
        val visitedE01 = set.updateFromUnvisitedToVisited(e01)
        set.calculateCost(visitedE01, pos00)
        set.calculateCost(visitedE01, pos02)
        if (withDebug) set.printEntries("calc cost from $pos01 to $pos00 and $pos02")

        val e02 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(pos02, e02.position)
        val visitedE02 = set.updateFromUnvisitedToVisited(e02)
        set.calculateCost(visitedE02, pos00, 3)
        set.calculateCost(visitedE02, pos03)
        if (withDebug) set.printEntries("calc cost from $pos02 to $pos00 and $pos03")

        val e03 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(pos03, e03.position)
        val visitedE03 = set.updateFromUnvisitedToVisited(e03)

        assertNull(set.findUnvisitedEntryWithLowestKnownDistance())
        if (withDebug) set.printEntries("end")

        assertEquals(visitedE03.parent, visitedE02)
        assertEquals(visitedE03.parent!!.parent, visitedE01)
        assertEquals(visitedE03.parent!!.parent!!.parent, visitedStartEntry)
    }


    data class GraphEntry(val from: Position, val to: Position, val cost: Int = 1)

    private fun List<GraphEntry>.addReversePathToList() = this + this.map { GraphEntry(from = it.to, to = it.from, cost = it.cost) }

    @Test
    fun should_add_correctly_reverse_graph_entries() {
        val entries = listOf<GraphEntry>(
                GraphEntry(pos00, pos01),
                GraphEntry(pos00, pos02, 3),
                GraphEntry(pos01, pos02),
                GraphEntry(pos02, pos03))

        val entriesWithReversePath = entries.addReversePathToList()

        assertEquals(8, entriesWithReversePath.size)

        entries.forEach { entry ->
            assertTrue(entriesWithReversePath.find {
                entry.from == it.to && entry.to == it.from && entry.cost == it.cost
            } != null, "not found reverse entry for $entry instead following list exists: $entriesWithReversePath")
        }
    }

    @Suppress("ConstantConditionIf")
    @Test
    fun should_collect_shortest_path() {
        val withDebug = false

        val graph = listOf<GraphEntry>(
                GraphEntry(pos00, pos01),
                GraphEntry(pos00, pos02, 3),
                GraphEntry(pos01, pos02),
                GraphEntry(pos02, pos03)
        ).addReversePathToList()

        val set = DijsktraSet(setOf(pos00, pos01, pos02, pos03))
        if (withDebug) set.printEntries("init")

        set.updateStartPosition(pos00)
        if (withDebug) set.printEntries("updateStartPosition")

        for (unvisited in set.unvisitedWithLowestDistance) {
            val visitedEntry = set.updateFromUnvisitedToVisited(unvisited)
            if (withDebug) set.printEntries("update from unvisted to visited (${visitedEntry.position})")

            for (graphEntry in graph.filter { it.from == visitedEntry.position }) {
                set.calculateCost(visitedEntry, graphEntry.to, graphEntry.cost)
                if (withDebug) set.printEntries("calc cost from ${visitedEntry.position} to ${graphEntry.to}")
            }
        }
        val goal = set.getEntryOn(pos03)
        assertEquals(Distance(3), goal.distance)
        assertEquals(set.getEntryOn(pos02), goal.parent)
        assertEquals(set.getEntryOn(pos01), goal.parent!!.parent)
        assertEquals(set.getEntryOn(pos00), goal.parent!!.parent!!.parent)
        assertNull(goal.parent!!.parent!!.parent!!.parent)
    }

    @Suppress("ConstantConditionIf")
    @Test
    fun should_get_corrent_path_with_zero_distance_cost() {
        val withDebug = false

        val graph = listOf<GraphEntry>(
                GraphEntry(pos00, pos01),
                GraphEntry(pos00, pos02, 0),
                GraphEntry(pos01, pos02),
                GraphEntry(pos02, pos03)
        ).addReversePathToList()

        val set = DijsktraSet(setOf(pos00, pos01, pos02, pos03))
        if (withDebug) set.printEntries("init")

        set.updateStartPosition(pos00)
        if (withDebug) set.printEntries("updateStartPosition")

        for (unvisited in set.unvisitedWithLowestDistance) {
            val visitedEntry = set.updateFromUnvisitedToVisited(unvisited)
            if (withDebug) set.printEntries("update from unvisted to visited (${visitedEntry.position})")

            for (graphEntry in graph.filter { it.from == visitedEntry.position }) {
                set.calculateCost(visitedEntry, graphEntry.to, graphEntry.cost)
                if (withDebug) set.printEntries("calc cost from ${visitedEntry.position} to ${graphEntry.to}")
            }
        }
        val goal = set.getEntryOn(pos03)
        assertEquals(Distance(1), goal.distance)
        assertEquals(set.getEntryOn(pos02), goal.parent)
        //ignore additional parent entries because of zero cost
        assertEquals(set.getEntryOn(pos00).position, goal.parent!!.parent!!.position)
        assertNull(goal.parent!!.parent!!.parent)
    }


    @Test
    fun should_solve_angle_level(): dynamic = runAsyncTest {
        val result = Dijkstra().solveWithoutDelay(Board.angleLevelWithGoalOn_x13_y3())
        assertTrue(shortestPathValidatorFor_x13_y3(result,withStartTile = true))
    }

    @Test
    fun should_return_false_for_unsolvable_maze(): dynamic = runAsyncTest {
        val result = Dijkstra().solveWithoutDelay(Board.unsolvableWallLevel())
        assertFalse(result.solved)
        assertEquals(0, result.path.size)
    }

}
