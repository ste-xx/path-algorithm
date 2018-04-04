package board

import tiles.*
import kotlin.reflect.KClass

class Board private constructor(ui: BoardUi, tilesSupplier: () -> Array<Array<Tile>>) {

    data class BoardSize(val width: Int, val height: Int) {
        constructor(tiles: Array<Array<Tile>>) : this(tiles.size, tiles[0].size)
        internal fun isInBoard(position: Position): Boolean = (position.x < width && position.y < height) && (position.x >= 0 && position.y >= 0)
    }

    data class NeighbourTiles(val top: Tile, val right: Tile, val bottom: Tile, val left: Tile) {
        fun toList(): List<Tile> = listOf(top, right, bottom, left)
    }

    class BoardInitException(s: String) : Throwable(s)
    class BoardAccessException(s: String) : Throwable(s)
    class BoardPathException(s: String) : Throwable(s)
    class BoardSetException(s: String) : Throwable(s)

    private val tiles: Array<Array<Tile>> = tilesSupplier()
    private val boardSize: BoardSize
    private val ui: BoardUi

    init {
        if (tiles.isEmpty())
            throw BoardInitException("Outer Array is empty")
        //check all tiles have same size
        if (tiles[0].isEmpty()) {
            throw BoardInitException("Inner Array is empty: $tiles[0]")
        }

        tiles.forEachIndexed { x, innerTiles ->
            innerTiles.forEachIndexed { y, tile ->
                if (tile.position != Position(x, y)) {
                    throw BoardInitException("Misconfiguration of Tiles: TilePos: ${tile.position} != ${Position(x, y)}")
                }
            }
        }

        tiles.reduce { p, c ->
            when {
                p.size != c.size -> throw BoardInitException("Columns does not have same size: ${p.size} != ${c.size}")
                else -> c
            }
        }

        boardSize = BoardSize(tiles);
        ui.draw(tiles)
        this.ui = ui

    }

    companion object Factory {
        fun createEmpty(size: BoardSize, ui: BoardUi) = Board(ui) {
            Array(size.width) { x ->
                Array(size.height) { y ->
                    EmptyTile(position = Position(x, y)) as Tile
                }
            }
        }

        fun create(ui: BoardUi, supplier: () -> Array<Array<Tile>>) = Board(ui, supplier)
    }

    fun boardSize(): BoardSize = boardSize

    fun isPlayable(): Boolean {
        val flattenTiles = tiles.flatten()

        return flattenTiles.filter { it is StartTile }.size == 1 &&
                flattenTiles.filter { it is GoalTile }.size == 1
    }

    fun getTileOn(position: Position) = when {
        boardSize.isInBoard(position) -> tiles[position.x][position.y]
        else -> throw BoardAccessException("$position is not inside of $boardSize")
    }

    fun getTileOn(supplier: () -> Position) = getTileOn(supplier())
    fun getTileOn(x: Int, y: Int) = getTileOn({ Position(x, y) })

    private fun setTile(tile: Tile) = when {
        boardSize.isInBoard(tile.position) -> tiles[tile.position.x][tile.position.y] = tile
        else -> throw BoardSetException("Tile position2 ${tile.position} is not inside of $boardSize")
    }

    fun getNeighboursOn(position: Position): NeighbourTiles {

        when {
            !boardSize.isInBoard(position) -> throw BoardAccessException("$position is not inside of $boardSize")
        }

        val neighbours = NeighbourTiles(
                top = when {
                    position.y - 1 < 0 -> WallTile(position = Position(position.x, position.y - 1))
                    else -> getTileOn(Position(position.x, position.y - 1))
                },
                right = when {
                    position.x + 1 > (boardSize.width - 1) -> WallTile(position = Position(position.x + 1, position.y))
                    else -> getTileOn(Position(position.x + 1, position.y))
                },
                bottom = when {
                    position.y + 1 > (boardSize.height - 1) -> WallTile(position = Position(position.x, position.y + 1))
                    else -> getTileOn(Position(position.x, position.y + 1))
                },
                left = when {
                    position.x - 1 < 0 -> WallTile(position = Position(position.x - 1, position.y))
                    else -> getTileOn(Position(position.x - 1, position.y))
                }
        )

        neighbours.toList()
                .filter { it is EmptyTile }
                .map { WatchedTile(it.position) }
                .forEach(this::setTile)

        val visitedTile = this.getTileOn(position)
        when (visitedTile) {
            is WalkableTile -> this.setTile(VisitedTile(visitedTile.position))
        }

        drawUi()

        return neighbours
    }

    fun getNeighboursOn(supplier: () -> Position) = getNeighboursOn(supplier())
    fun getNeighboursOn(x: Int, y: Int) = getNeighboursOn({ Position(x, y) })


    fun <T : UniqueTile> findUniqueTile(clazz: KClass<T>): T {
        if (!isPlayable()) {
            throw IllegalStateException("Board is not playable.")
        }

        val result = tiles.flatten().find { clazz.isInstance(it) }

        //suppress
        @Suppress("UNCHECKED_CAST")
        return when {
            clazz.isInstance(result) -> result as T //result is T because of isInstance check
            else -> throw IllegalStateException("Unique tile not found")
        }
    }

    fun findStart() = findUniqueTile(StartTile::class)
    fun findGoal() = findUniqueTile(GoalTile::class)

    fun setPath(path: Path) {
        for (position in path) {
            when {
                !boardSize.isInBoard(position) -> throw BoardPathException("Path element $position is outside of the board $boardSize")
                getTileOn(position) is WallTile -> throw BoardPathException("Path element $position is a wall tile")
            }
        }

        path.filter { WalkableTile::class.isInstance(getTileOn(it)) }
                .map { PathTile(it) }
                .forEach(this::setTile)

        drawUi()
    }

    private fun drawUi() {
        this.ui.draw(tiles)
    }
}