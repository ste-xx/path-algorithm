package board

import external.Rough
import external.RoughCanvas
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import tiles.EmptyTile
import tiles.RoughJsLegend
import tiles.Tile
import kotlin.browser.document
import kotlin.browser.window

object RoughJsBoard : BoardUi {

    val maze: RoughCanvas
    val mazeCanvas: HTMLCanvasElement = document.getElementById("maze") as HTMLCanvasElement

    init {
        adjustSize(null)
        maze = Rough.canvas(mazeCanvas)
//        window.requestAnimationFrame(::adjustSize)
    }


    private fun adjustSize(e: Double?){
        mazeCanvas.width = (window.innerWidth / 5) * 3
        val footerAndHeaderSize = 200
        mazeCanvas.height = window.innerHeight - footerAndHeaderSize

        console.log("resize");

        if(e != null) draw( Array(0) { x ->
            Array(0) { y ->
                EmptyTile(x, y) as Tile
            }
        })
    }


    override fun draw(tiles: Array<Array<Tile>>) {

        val xOffset = 0
        val yOffset = 0
        val tileSize = calculateTileSize(tiles)

        val ctx: CanvasRenderingContext2D = (mazeCanvas.getContext("2d") as CanvasRenderingContext2D?)!!
        ctx.clearRect(0.0, 0.0, 1400.0, 1400.0)

        tiles.forEachIndexed { columnIndex: Int, rows: Array<Tile> ->
            rows.forEachIndexed { rowIndex: Int, tile: Tile ->
                maze.rectangle(tileSize * columnIndex + xOffset, tileSize * rowIndex + yOffset, tileSize, tileSize, tile.shapeOptions)
            }
        }
    }

    private fun calculateTileSize(tiles: Array<Array<Tile>>): Int {
        val minCanvasLength = when {
            mazeCanvas.width < mazeCanvas.height -> mazeCanvas.width
            else -> mazeCanvas.height
        }

        val minTileLengh = when {
            tiles.size < tiles[0].size -> tiles.size
            else -> tiles[0].size
        }
        return minCanvasLength / minTileLengh;
    }

}