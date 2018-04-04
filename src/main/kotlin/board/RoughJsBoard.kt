package board

import external.Rough
import external.RoughCanvas
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import tiles.Tile
import kotlin.browser.document

object RoughJsBoard : BoardUi {

    val maze: RoughCanvas
    val mazeCanvas: HTMLCanvasElement = document.getElementById("maze") as HTMLCanvasElement

    init {
        maze = Rough.canvas(mazeCanvas)
    }


    override fun draw(tiles: Array<Array<Tile>>) {
        val xOffset = 40
        val yOffset = 40
        val tileSize = 80

        val ctx: CanvasRenderingContext2D = (mazeCanvas.getContext("2d") as CanvasRenderingContext2D?)!!
        ctx.clearRect(0.0, 0.0, 1400.0, 1400.0)

        tiles.forEachIndexed { columnIndex: Int, rows: Array<Tile> ->
            rows.forEachIndexed { rowIndex: Int, tile: Tile ->
                maze.rectangle(tileSize * columnIndex + xOffset, tileSize * rowIndex + yOffset, tileSize, tileSize, tile.shapeOptions)
            }
        }
    }

}