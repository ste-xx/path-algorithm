package ui

import tiles.Tile

interface BoardUi {
    fun drawTiles(tiles: Array<Array<Tile>>)

}