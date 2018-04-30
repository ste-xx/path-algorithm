package tiles


class Path(vararg positions: Position) {
    constructor(supplier: () -> Position) : this(supplier())
    constructor(x: Int, y: Int) : this(Position(x, y))

    companion object Factory {
        public val EMPTY_PATH = Path()
    }

    val positions: List<Position>
    val size: Int

    init {
        val tmpList = positions.distinct().toMutableList()
        tmpList.sortWith(compareBy(Position::x, Position::y))
        this.positions = tmpList
        size = this.positions.size
    }

    operator fun plus(path: Path) = Path(*(this.positions + path.positions).toTypedArray())
    operator fun iterator()=positions.iterator()

    inline fun filter(predicate: (Position) -> Boolean) = positions.filter(predicate)

    fun isValid(): Boolean {
        positions.reduce { p, c ->
            when {
                (p.x - c.x) in -1..1 && (p.y - c.y) in -1..1 -> c
                else -> return false
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as Path

        if (positions != other.positions) return false

        return true
    }

    override fun hashCode(): Int {
        return positions.hashCode()
    }

    override fun toString(): String {
        return "Path(positions=$positions)"
    }


}