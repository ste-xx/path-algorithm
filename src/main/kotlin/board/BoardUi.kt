package board

import tiles.Tile

interface BoardUi {
    fun draw(tiles: Array<Array<Tile>>)
}