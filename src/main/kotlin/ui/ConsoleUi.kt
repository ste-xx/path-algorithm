package ui

import tiles.EmptyTile
import tiles.Tile

object ConsoleUi : BoardUi {
    override fun drawTiles(tiles: Array<Array<Tile>>) {

        val rotated: Array<Array<Tile>> = Array(tiles[0].size) {x->
            Array(tiles.size) {y-> EmptyTile(x, y) as Tile }
        }

        for (i in 0 until tiles[0].size) {
            for (j in tiles.size - 1 downTo 0) {
                rotated[i][j] = tiles[j][i]
            }
        }

        rotated.forEach {
            console.log(it.joinToString(separator = "") {
                it.print
            })
        }
        console.log()
    }

}