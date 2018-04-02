package algorithm

import board.Board
import level.*
import testutil.runAsyncTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BreathFirstSearchTest {

    @Test
    fun should_solve_angle_level(): dynamic = runAsyncTest {

        val bfs = BreathFirstSearch()
        val board = Board.angleLevelWithGoalOn_x13_y3()

        val expectedPath = shortestPathForAngleLevelWithGoalOn_x13_y3()

        val result = bfs.solveWithoutDelay(board)
        assertTrue(result.solved)
        assertEquals(expectedPath.size, result.path.size)
        for (position in result.path) {
            assertTrue(expectedPath.contains(position))
        }

    }

    @Test
    fun should_return_false_for_unsolvable_maze(): dynamic = runAsyncTest {
        val bfs = BreathFirstSearch()
        val board = Board.unsolvableWallLevel()

        val result = bfs.solveWithoutDelay(board)
        assertFalse(result.solved)
        assertEquals(0, result.path.size)
    }

}