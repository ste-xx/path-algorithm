package ui

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import tiles.*
import kotlin.browser.document

object RoughJsUi : BoardUi {

    val maze: RoughCanvas
    val legend: RoughCanvas
    val mazeCanvas: HTMLCanvasElement

    init {
        mazeCanvas = document.getElementById("maze") as HTMLCanvasElement
        maze = Rough.canvas(mazeCanvas)
        legend = Rough.canvas(document.getElementById("legend") as HTMLCanvasElement)

        drawLegend()
    }

    private fun drawLegend() {
        val tileSize = 80
        val yOffset = 40
        val yOffsetForItems = yOffset + tileSize
        val gap = 90
        val kindOfTiles = listOf(EmptyTile(-1, -1),
                StartTile(-1, -1),
                GoalTile(-1, -1),
                WatchedTile(-1,-1),
                VisitedTile(-1,-1),
                WallTile(-1, -1))

        legend.rectangle(0, yOffset, tileSize * 3, tileSize * (kindOfTiles.size + 1) * 2)

        kindOfTiles.forEachIndexed { index, tile ->
            legend.rectangle(tileSize, yOffsetForItems + index * tileSize + gap * index, tileSize, tileSize, tile.shapeOptions)
        }
    }

    override fun drawTiles(tiles: Array<Array<Tile>>) {
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