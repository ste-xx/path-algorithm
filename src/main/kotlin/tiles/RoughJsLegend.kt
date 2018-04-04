package tiles

import external.Rough
import external.RoughCanvas
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document

object RoughJsLegend : LegendUi {

    val legend: RoughCanvas = Rough.canvas(document.getElementById("legend") as HTMLCanvasElement)

    override fun draw() {
        val tileSize = 80
        val yOffset = 40
        val yOffsetForItems = yOffset + tileSize
        val gap = 90
        val kindOfTiles = listOf(EmptyTile(-1, -1),
                StartTile(-1, -1),
                GoalTile(-1, -1),
                WatchedTile(-1, -1),
                VisitedTile(-1, -1),
                WallTile(-1, -1))

        legend.rectangle(0, yOffset, tileSize * 3, tileSize * (kindOfTiles.size + 1) * 2)

        kindOfTiles.forEachIndexed { index, tile ->
            legend.rectangle(tileSize, yOffsetForItems + index * tileSize + gap * index, tileSize, tileSize, tile.shapeOptions)
        }
    }
}