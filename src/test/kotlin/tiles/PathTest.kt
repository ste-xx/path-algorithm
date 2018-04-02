package tiles

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PathTest {


    @Test
    fun should_transform_to_position() {
        val expected = listOf(Position(0, 0), Position(1, 0))
        val path = Path(Position(0, 0), Position(1, 0))

        assertEquals(expected, path.positions)
        assertTrue(path.isValid())
        assertEquals(path.size,2)
    }

    @Test
    fun same_positions_should_be_equal() {
        val o1 = Path(Position(0, 0), Position(1, 0))
        val o2 = Path(Position(0, 0), Position(1, 0))

        assertEquals(o1, o2)
    }

    @Test
    fun should_detect_gaps() {
        val path = Path(Position(0, 0), Position(2, 0))
        assertFalse(path.isValid())
    }

    @Test
    fun should_sorted_start_with_lowest_x_then_prefer_lowest_y(){
        val expected = listOf(Position(0,0), Position(0,1), Position(1,0), Position(1,1))
        val expectedEqualPath = Path(*expected.toTypedArray())

        val path = Path(Position(1,1),
                Position(0,1),
                Position(1,0),
                Position(0,0))

        assertEquals(expectedEqualPath, path)
        assertEquals(expected, path.positions)
        assertEquals(path.size, 4)
    }


    @Test
    fun should_add_cumulative(){
        val expected = Path(Position(0,0), Position(1,0))
        val pathAdd = Path(0,0)+Path(1,0)
        val pathCumulative  = Path(1,0)+Path(0,0)

        assertEquals(expected, pathAdd)
        assertEquals(expected, pathCumulative)
        assertEquals(pathAdd.size, 2)
    }

    @Test
    fun should_be_sorted_after_add() {
        val expected = Path(Position(0, 0), Position(1, 0), Position(2, 0))
        val pathAdd = Path(2, 0) + Path(0, 0) + Path(1, 0)
        assertEquals(expected, pathAdd)
        assertEquals(pathAdd.size, 3)
        assertTrue(pathAdd.isValid())
    }

    @Test
    fun should_not_add_already_added_positions(){
        val expected = Path(Position(0,0))
        val pathAdd = Path(0,0)+Path(0,0)

        assertEquals(expected, pathAdd)
        assertEquals(pathAdd.size, 1)
    }


}
