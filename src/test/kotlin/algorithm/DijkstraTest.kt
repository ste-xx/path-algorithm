package algorithm

import board.Board
import level.*
import testutil.runAsyncTest
import tiles.Position
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DijkstraTest {

    @Test
    fun should_set_hold_same_parent_after_parent_is_updated(){

        val positionSet =mutableSetOf(Position(0,0),Position(0,1),Position(0,2))

        val set = Dijkstra.DijsktraSet(positionSet)
        set.updateStartPosition(Position(0,0))
        set.printEntries("update Start")
        set.calculateCost(set.iterator().next(),Position(0,1))
        set.printEntries("calc cost")
        assertTrue(false)
    }


    @Test
    fun should_solve_angle_level(): dynamic = runAsyncTest {

        val dijkstra = Dijkstra()
        val board = Board.angleLevelWithGoalOn_x13_y3()

        val expectedPath = shortestPathForAngleLevelWithGoalOn_x13_y3()

        val result = dijkstra.solveWithoutDelay(board)
        assertTrue(result.solved)
        assertEquals(expectedPath.size, result.path.size)
        for (position in result.path) {
            assertTrue(expectedPath.contains(position))
        }

    }

    @Test
    fun should_return_false_for_unsolvable_maze(): dynamic = runAsyncTest {
        val dijkstra = Dijkstra()
        val board = Board.unsolvableWallLevel()

        val result = dijkstra.solveWithoutDelay(board)
        assertFalse(result.solved)
        assertEquals(0, result.path.size)
    }

}