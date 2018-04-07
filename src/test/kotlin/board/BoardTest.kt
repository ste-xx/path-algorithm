package board

import board.Board.*
import level.*
import tiles.*
import kotlin.test.*

class BoardTest {


    @Test
    fun should_create_correct_board_size() {

        val board = Board.createEmpty(ConsoleBoard, BoardSize(10, 5))

        assertEquals(BoardSize(10, 5), board.boardSize())
        assertEquals(EmptyTile(7, 4), board.getTileOn(7, 4))

    }

    @Test
    fun is_not_playable_with_multiple_start_tiles() {
        assertFalse(Board.createWithConsoleUi(BoardInitializer(BoardSize(2, 2)) { x, y -> StartTile(x, y) }).isPlayable())
    }

    @Test
    fun is_not_playable_without_start_tiles() {
        assertFalse(Board.createWithConsoleUi(BoardInitializer(BoardSize(2, 2)) { x, y ->
            when {
                x == 0 && y == 0 -> GoalTile(x, y)
                else -> EmptyTile(x, y)
            }
        }).isPlayable())
    }

    @Test
    fun is_not_playable_with_multiple_goal_tiles() {
        assertFalse(Board.createWithConsoleUi(BoardInitializer(BoardSize(2, 2)) { x, y -> GoalTile(x, y) }).isPlayable())
    }

    @Test
    fun is_not_playable_without_goal_tiles() {
        assertFalse(Board.createWithConsoleUi(BoardInitializer(BoardSize(2, 2)) { x, y ->
            when {
                x == 0 && y == 0 -> StartTile(x, y)
                else -> EmptyTile(x, y)
            }
        }).isPlayable())
    }

    @Test
    fun is_playable_with_start_and_goal_tile() {
        assertTrue(Board.createWithConsoleUi(BoardInitializer(BoardSize(2, 2)) { x, y ->
            when {
                x == 0 && y == 0 -> StartTile(x, y)
                x == 0 && y == 1 -> GoalTile(x, y)
                else -> EmptyTile(x, y)
            }
        }).isPlayable())
    }


    @Test
    fun should_create_an_exception_on_wrong_board_size() {

        assertFailsWith(BoardInitException::class) {
            Board.createEmptyWithConsoleUi(BoardSize(0, 5))
        }
        assertFailsWith(BoardInitException::class) {
            Board.createEmptyWithConsoleUi(BoardSize(0, 5))
        }

        assertFailsWith(BoardInitException::class) {
            Board.createEmptyWithConsoleUi(BoardSize(0, 5))
        }
    }

    @Test
    fun should_create_an_exception_on_access_of_outside_the_board() {
        val board = Board.createEmpty(ConsoleBoard, BoardSize(10, 5))


        assertFailsWith(BoardAccessException::class) {
            board.getTileOn(500, 2)
        }

        assertFailsWith(BoardAccessException::class) {
            board.getTileOn(2, 500)
        }
    }

    @Test
    fun should_create_a_simple_test_level() {
        val board = Factory.angleLevelWithGoalOn_x11_y1()
        assertEquals(BoardSize(15, 12), board.boardSize())
    }

    @Test
    fun should_get_correct_tile_on_position() {
        val board = Factory.angleLevelWithGoalOn_x11_y1()
        val startTile = board.getTileOn(2, 11)
        val goalTile = board.getTileOn(11, 1)
        val emptyTile = board.getTileOn(0, 0)

        assertTrue(startTile is StartTile)
        assertEquals(Position(2, 11), startTile.position)

        assertTrue(goalTile is GoalTile)
        assertEquals(Position(11, 1), goalTile.position)

        assertTrue(emptyTile is EmptyTile)
        assertEquals(Position(0, 0), emptyTile.position)

    }

    @Test
    fun should_find_correct_unique_tile() {
        val board = Factory.angleLevelWithGoalOn_x11_y1()
        val startTile = board.getTileOn(2, 11)
        val goalTile = board.getTileOn(11, 1)

        assertEquals(startTile, board.findUniqueTile(StartTile::class))
        assertEquals(goalTile, board.findUniqueTile(GoalTile::class))
    }


    @Test
    fun should_get_correct_neighbours_for_tile() {
        val board = Factory.neighbourLevel()

        val middle = board.getTileOn(1, 1)

        val middleNeighbours = board.getNeighboursOn(middle::position)
        assertEquals(middleNeighbours.top.position, Position(1, 0))
        assertTrue(WalkableTile::class.isInstance(middleNeighbours.top))

        assertEquals(middleNeighbours.right.position, Position(2, 1))
        assertTrue(GoalTile::class.isInstance(middleNeighbours.right))

        assertEquals(middleNeighbours.bottom.position, Position(1, 2))
        assertTrue(StartTile::class.isInstance(middleNeighbours.bottom))

        assertEquals(middleNeighbours.left.position, Position(0, 1))
        assertTrue(WallTile::class.isInstance(middleNeighbours.left))


        val topLeft = board.getTileOn(0, 0)
        val topLeftNeighbours = board.getNeighboursOn(topLeft::position)
        assertEquals(topLeftNeighbours.top.position, Position(0, -1))
        assertTrue(WallTile::class.isInstance(topLeftNeighbours.top))

        assertEquals(topLeftNeighbours.right.position, Position(1, 0))
        assertTrue(WalkableTile::class.isInstance(topLeftNeighbours.right))

        assertEquals(topLeftNeighbours.bottom.position, Position(0, 1))
        assertTrue(WallTile::class.isInstance(topLeftNeighbours.bottom))

        assertEquals(topLeftNeighbours.left.position, Position(-1, 0))
        assertTrue(WallTile::class.isInstance(topLeftNeighbours.left))


        val topRight = board.getTileOn(2, 0)
        val topRightNeighbours = board.getNeighboursOn(topRight::position)
        assertEquals(topRightNeighbours.top.position, Position(2, -1))
        assertTrue(WallTile::class.isInstance(topRightNeighbours.top))

        assertEquals(topRightNeighbours.right.position, Position(3, 0))
        assertTrue(WallTile::class.isInstance(topRightNeighbours.right))

        assertEquals(topRightNeighbours.bottom.position, Position(2, 1))
        assertTrue(GoalTile::class.isInstance(topRightNeighbours.bottom))

        assertEquals(topRightNeighbours.left.position, Position(1, 0))
        assertTrue(WalkableTile::class.isInstance(topRightNeighbours.left))

        val bottomRight = board.getTileOn(2, 2)
        val bottomRightNeighbours = board.getNeighboursOn(bottomRight::position)
        assertEquals(bottomRightNeighbours.top.position, Position(2, 1))
        assertTrue(GoalTile::class.isInstance(bottomRightNeighbours.top))

        assertEquals(bottomRightNeighbours.right.position, Position(3, 2))
        assertTrue(WallTile::class.isInstance(bottomRightNeighbours.right))

        assertEquals(bottomRightNeighbours.bottom.position, Position(2, 3))
        assertTrue(WallTile::class.isInstance(bottomRightNeighbours.bottom))

        assertEquals(bottomRightNeighbours.left.position, Position(1, 2))
        assertTrue(StartTile::class.isInstance(bottomRightNeighbours.left))

        val bottomLeft = board.getTileOn(0, 2)
        val bottomLeftNeighbours = board.getNeighboursOn(bottomLeft::position)
        assertEquals(bottomLeftNeighbours.top.position, Position(0, 1))
        assertTrue(WallTile::class.isInstance(bottomLeftNeighbours.top))

        assertEquals(bottomLeftNeighbours.right.position, Position(1, 2))
        assertTrue(StartTile::class.isInstance(bottomLeftNeighbours.right))

        assertEquals(bottomLeftNeighbours.bottom.position, Position(0, 3))
        assertTrue(WallTile::class.isInstance(bottomLeftNeighbours.bottom))

        assertEquals(bottomLeftNeighbours.left.position, Position(-1, 2))
        assertTrue(WallTile::class.isInstance(bottomLeftNeighbours.left))

        assertFailsWith(BoardAccessException::class) { board.getNeighboursOn(-1, -1) }

    }

    @Test
    fun should_set_path() {
        val board = Factory.wallLevel()

        board.setPath(Path(Position(0, 0), Position(1, 0), Position(2, 0)))
        assertEquals(StartTile(0, 0), board.getTileOn(0, 0), "StartTile is not modified")
        assertEquals(PathTile(1, 0), board.getTileOn(1, 0), "EmptyTile to PathTile")
        assertEquals(GoalTile(2, 0), board.getTileOn(2, 0), "GoalTile is not modified")

        assertFailsWith(BoardPathException::class, "Can not set path through wall tile") {
            board.setPath(Path(Position(0, 1)))
        }

        assertFailsWith(BoardPathException::class, "Can not set path outside of the board") {
            board.setPath(Path(Position(-1, -1)))
        }


    }
}
