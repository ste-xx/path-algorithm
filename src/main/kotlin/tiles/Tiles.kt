@file:Suppress("DataClassPrivateConstructor")

package tiles

import ui.ShapeOptions

interface Tile {
    val print: String
    val shapeOptions: ShapeOptions
    val position: Position
}

interface UniqueTile : Tile
interface WalkableTile : Tile

data class StartTile private constructor(val name: String,
                                         override val print: String,
                                         override val shapeOptions: ShapeOptions,
                                         override val position: Position) : UniqueTile {
    constructor(position: Position) : this("Start", "▧", ShapeOptions(fill = "#ff0000"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))
}


data class GoalTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : UniqueTile {
    constructor(position: Position) : this("Goal", "▨", ShapeOptions(fill = "#EE82EE"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}

data class WallTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : Tile {
    constructor(position: Position) : this("Wall", "■", ShapeOptions(fill = "#000000"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}

data class EmptyTile internal constructor(val name: String,
                                          override val print: String,
                                          override val shapeOptions: ShapeOptions,
                                          override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Empty", "□", ShapeOptions(fill = "#F5F5F5"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}

data class WatchedTile private constructor(val name: String,
                                           override val print: String,
                                           override val shapeOptions: ShapeOptions,
                                           override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Watched", "▩", ShapeOptions(fill = "#00ff00"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}


data class VisitedTile private constructor(val name: String,
                                           override val print: String,
                                           override val shapeOptions: ShapeOptions,
                                           override val position: Position) : WalkableTile {
    constructor(position: Position) : this("VisitedTile", "▩", ShapeOptions(fill = "#0000FF"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}

data class PathTile private constructor(val name: String,
                                        override val print: String,
                                        override val shapeOptions: ShapeOptions,
                                        override val position: Position) : WalkableTile {
    constructor(position: Position) : this("Path", "▣", ShapeOptions(fill = "#FFA500"), position)
    constructor(x: Int, y: Int) : this(Position(x, y))

}
