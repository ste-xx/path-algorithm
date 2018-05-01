@file:Suppress("DataClassPrivateConstructor")

package tiles

import external.ShapeOptions

interface Tile {
    val print: String
    val shapeOptions: ShapeOptions
    val position: Position
}

interface UniqueTile : Tile
interface WalkableTile : Tile {
    fun getAsVisited():Tile
}
interface VisitedTile : Tile

data class StartTile private constructor(val name: String,
                                         override val print: String,
                                         override val shapeOptions: ShapeOptions,
                                         override val position: Position) : UniqueTile {
    constructor(position: Position) : this("Start", "▧", ShapeOptions(fill = "#ff0000"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)

}


data class GoalTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : UniqueTile {
    constructor(position: Position) : this("Goal", "▨", ShapeOptions(fill = "#EE82EE"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)

}

data class WallTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : Tile {
    constructor(position: Position) : this("Wall", "■", ShapeOptions(fill = "#000000"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)

}

data class EmptyTile internal constructor(val name: String,
                                          override val print: String,
                                          override val shapeOptions: ShapeOptions,
                                          override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Empty", "□", ShapeOptions(fill = "#F5F5F5"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)

    override fun getAsVisited() = VisitedEmptyTile(this)

}

data class WatchedEmptyTile private constructor(val name: String,
                                                override val print: String,
                                                override val shapeOptions: ShapeOptions,
                                                override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Watched", "▩", ShapeOptions(fill = "#00ff00"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)
    override fun getAsVisited() = VisitedEmptyTile(this)

}

data class VisitedEmptyTile private constructor(val name: String,
                                                override val print: String,
                                                override val shapeOptions: ShapeOptions,
                                                override val position: Position) : WalkableTile, VisitedTile {
    constructor(position: Position) : this("VisitedEmptyTile", "▩", ShapeOptions(fill = "#0000FF"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)

    override fun getAsVisited() = this
}

data class MudTile internal constructor(val name: String,
                                          override val print: String,
                                          override val shapeOptions: ShapeOptions,
                                          override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Mud", "◇", ShapeOptions(fill = "#F5DEB3"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)
    override fun getAsVisited() = VisitedMudTile(this)

}

data class WatchedMudTile private constructor(val name: String,
                                                override val print: String,
                                                override val shapeOptions: ShapeOptions,
                                                override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Watched", "◈", ShapeOptions(fill = "#964B00"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)
    override fun getAsVisited() = VisitedMudTile(this)

}


data class VisitedMudTile private constructor(val name: String,
                                              override val print: String,
                                              override val shapeOptions: ShapeOptions,
                                              override val position: Position) : WalkableTile,VisitedTile {
    constructor(position: Position) : this("VisitedEmptyTile", "◆", ShapeOptions(fill = "#A52A2A"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)
    override fun getAsVisited() = this

}


data class PathTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Path", "▣", ShapeOptions(fill = "#FFA500"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
    constructor(other: Tile) : this(other.position)
    override fun getAsVisited() = this


}
