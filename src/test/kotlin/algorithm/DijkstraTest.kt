package algorithm

import algorithm.Dijkstra.*
import algorithm.Dijkstra.Distance.ConstDistances.UNKNOWN
import board.Board
import level.*
import testutil.runAsyncTest
import tiles.Path
import tiles.Position
import kotlin.test.*

private const val withDebug = false

class DijkstraTest {

    private fun DijsktraSet.printEntries(header: String) = when {
        withDebug -> (listOf("--------$header---------") + this.positionToEntry.toList()).forEach(::println)
        else -> Unit
    }

    private val node00 = Position(0, 0)
    private val node01 = Position(0, 1)
    private val node02 = Position(0, 2)
    private val node03 = Position(0, 3)

    @Test
    fun check_distance_equality() {
        assertFalse(Distance(0) == UNKNOWN, "UNKNOWN is not Distance(0)")

        assertTrue(Distance(0) == Distance(0), "Distance(0) is Distance(0)")
        assertFalse(Distance(1) == Distance(0), "Distance(1) is not Distance(0)")
    }

    @Test
    fun dijkstra_entries_iterator() {
        val entry = Entry(distance = Distance(2), node = node02,
                parent = Entry(distance = Distance(1), node = node01,
                        parent = Entry(distance = Distance(0), node = node00)))
        //exhaust iterator
        for (e in entry) { }
        //new iterator
        assertTrue(entry.iterator().hasNext())
    }

    @Test
    fun dijkstra_entries_to_path() {
        val entry = Entry(distance = Distance(2), node = node02,
                parent = Entry(distance = Distance(1), node = node01,
                        parent = Entry(distance = Distance(0), node = node00)))

        val expected = Path(node00) + Path(node01) + Path(node02)
        assertEquals(expected, entry.toPath())
    }

    @Test
    fun should_throw_an_error_when_calculate_cost_will_be_called_with_an_unvisted_node() {
        val set = DijsktraSet(setOf(node00, node01, node02), node00)
        assertFailsWith(IllegalArgumentException::class) {
            set.calculateCost(set.getEntryOn(node00), node01)
        }
    }

    @Test
    fun should_get_shortest_path_with_manual_execution() {
//        (0,0) <-> (0,1) -> cost 1
//        (0,1) <-> (0,2) -> cost 1
//        (0,0) <-> (0,2) -> cost 3
//        (0,2) <-> (0,3) -> cost 1
        val set = DijsktraSet(nodes = setOf(node00, node01, node02, node03), startNode = node00)
        set.printEntries("init")

        val startEntry = set.getEntryOn(node00)
        val visitedStartEntry = set.updateEntryToVisited(startEntry)
        set.printEntries("update from unvisited to visited")
        set.calculateCost(visitedStartEntry, node01)
        set.calculateCost(visitedStartEntry, node02, 3)
        set.printEntries("calc cost from $startEntry to $node01 and $node02")

        val e01 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(node01, e01.node)
        val visitedE01 = set.updateEntryToVisited(e01)
        set.calculateCost(visitedE01, node00)
        set.calculateCost(visitedE01, node02)
        set.printEntries("calc cost from $node01 to $node00 and $node02")

        val e02 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(node02, e02.node)
        val visitedE02 = set.updateEntryToVisited(e02)
        set.calculateCost(visitedE02, node00, 3)
        set.calculateCost(visitedE02, node03)
        set.printEntries("calc cost from $node02 to $node00 and $node03")

        val e03 = set.findUnvisitedEntryWithLowestKnownDistance()!!
        assertEquals(node03, e03.node)
        val visitedE03 = set.updateEntryToVisited(e03)

        assertNull(set.findUnvisitedEntryWithLowestKnownDistance())
        set.printEntries("end")

        assertEquals(visitedE03.parent, visitedE02)
        assertEquals(visitedE03.parent!!.parent, visitedE01)
        assertEquals(visitedE03.parent!!.parent!!.parent, visitedStartEntry)
    }


    data class GraphEntry(val from: Position, val to: Position, val cost: Int = 1)

    private fun List<GraphEntry>.addReversePathToList() = this + this.map { GraphEntry(from = it.to, to = it.from, cost = it.cost) }

    @Test
    fun should_add_correctly_reverse_graph_entries() {
        val entries = listOf(
                GraphEntry(node00, node01),
                GraphEntry(node00, node02, 3),
                GraphEntry(node01, node02),
                GraphEntry(node02, node03))

        val entriesWithReversePath = entries.addReversePathToList()

        assertEquals(8, entriesWithReversePath.size)

        entries.forEach { entry ->
            assertTrue(entriesWithReversePath.find {
                entry.from == it.to && entry.to == it.from && entry.cost == it.cost
            } != null, "not found reverse entry for $entry instead following list exists: $entriesWithReversePath")
        }
    }

    @Test
    fun should_collect_shortest_path() {

        val graph = listOf(
                GraphEntry(node00, node01),
                GraphEntry(node00, node02, 3),
                GraphEntry(node01, node02),
                GraphEntry(node02, node03)
        ).addReversePathToList()

        val set = DijsktraSet(nodes = setOf(node00, node01, node02, node03), startNode = node00)
        set.printEntries("init")
        for (unvisited in set) {
            val visitedEntry = set.updateEntryToVisited(unvisited)
            set.printEntries("update from unvisted to visited (${visitedEntry.node})")

            for (graphEntry in graph.filter { it.from == visitedEntry.node }) {
                set.calculateCost(visitedEntry, graphEntry.to, graphEntry.cost)
                set.printEntries("calc cost from ${visitedEntry.node} to ${graphEntry.to}")
            }
        }
        val goal = set.getEntryOn(node03)
        assertEquals(Distance(3), goal.distance)
        assertEquals(set.getEntryOn(node02), goal.parent)
        assertEquals(set.getEntryOn(node01), goal.parent!!.parent)
        assertEquals(set.getEntryOn(node00), goal.parent!!.parent!!.parent)
        assertNull(goal.parent!!.parent!!.parent!!.parent)
    }

    @Test
    fun should_get_corrent_path_with_zero_distance_cost() {

        val graph = listOf(
                GraphEntry(node00, node01),
                GraphEntry(node00, node02, 0),
                GraphEntry(node01, node02),
                GraphEntry(node02, node03)
        ).addReversePathToList()

        val set = DijsktraSet(nodes = setOf(node00, node01, node02, node03), startNode = node00)
        set.printEntries("init")

        for (unvisited in set) {
            val visitedEntry = set.updateEntryToVisited(unvisited)
            set.printEntries("update from unvisted to visited (${visitedEntry.node})")

            for (graphEntry in graph.filter { it.from == visitedEntry.node }) {
                set.calculateCost(visitedEntry, graphEntry.to, graphEntry.cost)
                set.printEntries("calc cost from ${visitedEntry.node} to ${graphEntry.to}")
            }
        }
        val goal = set.getEntryOn(node03)
        assertEquals(Distance(1), goal.distance)
        assertEquals(set.getEntryOn(node02), goal.parent)
        //ignore additional parent entries because of zero cost
        assertEquals(set.getEntryOn(node00).node, goal.parent!!.parent!!.node)
        assertNull(goal.parent!!.parent!!.parent)
    }


    @Test
    fun should_solve_angle_level(): dynamic = runAsyncTest {
        val result = Dijkstra().solveWithoutDelay(Board.angleLevelWithGoalOn_x13_y3())
        assertTrue(shortestPathValidatorFor_x13_y3(result, withStartTile = true))
    }

    @Test
    fun should_return_false_for_unsolvable_maze(): dynamic = runAsyncTest {
        val result = Dijkstra().solveWithoutDelay(Board.unsolvableWallLevel())
        assertFalse(result.solved)
        assertEquals(0, result.path.size)
    }

}
