package board

import tiles.*
import kotlin.reflect.KClass

class Board private constructor(private val ui: BoardUi, initializer: Board.BoardInitializer) {

    class BoardInitException(s: String) : Throwable(s)
    class BoardAccessException(s: String) : Throwable(s)
    class BoardPathException(s: String) : Throwable(s)
    class BoardSetException(s: String) : Throwable(s)

    data class BoardSize(val width: Int, val height: Int) {
        internal fun isInBoard(position: Position): Boolean = (position.x < width && position.y < height) && (position.x >= 0 && position.y >= 0)
    }

    data class NeighbourTiles(val top: Tile, val right: Tile, val bottom: Tile, val left: Tile):Iterable<Tile> {
        fun toList(): List<Tile> = listOf(top, right, bottom, left)
        override operator fun iterator() = this.toList().iterator()
    }

    class BoardInitializer(val size: BoardSize, val producer: (x: Int, y: Int) -> Tile) {
        internal fun create(): Array<Array<Tile>> {
            when {
                size.width <= 0 -> throw BoardInitException("Width is smaller/equals to Zero")
                size.height <= 0 -> throw BoardInitException("Height is smaller/equals to Zero")
            }

            return Array(size.width) { x ->
                Array(size.height) { y ->
                    val tile = producer(x, y)
                    when {
                        tile.position != Position(x, y) -> throw BoardInitException("Misconfiguration of Tiles: TilePos: ${tile.position} != ${Position(x, y)}")
                        else -> tile
                    }
                }
            }
        }
    }

    private val tiles: Array<Array<Tile>> = initializer.create()
    private val boardSize: BoardSize = initializer.size

    init {
        ui.draw(tiles)
    }

    companion object Factory {

        fun createEmpty(ui: BoardUi, size: BoardSize) = Board(ui, BoardInitializer(size) { x, y ->
            EmptyTile(x, y)
        })

        fun create(ui: BoardUi, initializer: BoardInitializer) = Board(ui, initializer)
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
    fun getAllTiles():List<Tile> = tiles.flatten()

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