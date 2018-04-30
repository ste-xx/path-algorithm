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
        val result = BreathFirstSearch().solveWithoutDelay(Board.angleLevelWithGoalOn_x13_y3())
        assertTrue(shortestPathValidatorFor_x13_y3(result,withStartTile = true))
    }

    @Test
    fun should_return_false_for_unsolvable_maze(): dynamic = runAsyncTest {
        val result = BreathFirstSearch().solveWithoutDelay( Board.unsolvableWallLevel())
        assertFalse(result.solved)
        assertEquals(0, result.path.size)
    }

}