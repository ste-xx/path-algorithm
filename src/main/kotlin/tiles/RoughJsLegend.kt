package tiles

import FOOTER_AND_HEADER_SIZE
import external.Rough
import external.RoughCanvas
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.browser.window

object RoughJsLegend : LegendUi {

    val legend: RoughCanvas
    val legendCanvas = document.getElementById("legend") as HTMLCanvasElement

    val legendDescriptions = document.getElementsByClassName("legendDescription")

    val kindOfTiles: List<Tile>

    init {

        adjustSize()
        legend = Rough.canvas(legendCanvas)
        kindOfTiles = listOf(
                StartTile(-1, -1),
                GoalTile(-1, -1),
                WallTile(-1, -1),
                EmptyTile(-1, -1),
                WatchedEmptyTile(-1, -1),
                VisitedEmptyTile(-1, -1),
                MudTile(-1,-1),
                WatchedMudTile(-1,-1),
                VisitedMudTile(-1,-1))
        draw()
    }

    private fun adjustSize() {
        legendCanvas.width = (window.innerWidth / 5)
        legendCanvas.height = window.innerHeight - FOOTER_AND_HEADER_SIZE
    }

    override fun draw() {
        val tileSize = calculateTileSize()

        val boxSize = tileSize * 3
        legend.rectangle(0, 0, boxSize, legendCanvas.height)

        val gap = tileSize
        val yOffset = gap / 2
        val centerTiles = tileSize
        kindOfTiles.forEachIndexed { index, tile ->
            val yPositionOfTile = yOffset + gap * index + tileSize * index
            legend.rectangle(centerTiles, yPositionOfTile, tileSize, tileSize, tile.shapeOptions)

            val adjustToBottem = tileSize
            val descriptionElement = (legendDescriptions[index] as HTMLElement)
            descriptionElement.style.top = "${yPositionOfTile + adjustToBottem}px"

            val centerDescription = boxSize / 2 - descriptionElement.offsetWidth / 2
            descriptionElement.style.left = "${centerDescription}px"

        }

    }

    private fun calculateTileSize() = (legendCanvas.height / (kindOfTiles.size)) / 2
}