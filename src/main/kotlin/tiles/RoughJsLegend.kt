package tiles

import external.Rough
import external.RoughCanvas
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.browser.window

object RoughJsLegend : LegendUi {

    val legend: RoughCanvas
    val legendCanvas = document.getElementById("legend") as HTMLCanvasElement

    val legendDescriptions = document.getElementsByClassName("legendDescription")

    val kindOfTiles: List<Tile>

    init {

        adjustSize(initCall = true)
        legend = Rough.canvas(legendCanvas)
        kindOfTiles = listOf(EmptyTile(-1, -1),
                StartTile(-1, -1),
                GoalTile(-1, -1),
                WatchedTile(-1, -1),
                VisitedTile(-1, -1),
                WallTile(-1, -1))
        draw()
        window.requestAnimationFrame(::adjustSize)

    }

    private fun adjustSize(e: Double = 0.0, initCall: Boolean = false) {
        val footerAndHeaderSize = 200


        val pWidth = legendCanvas.width
        val pHeight = legendCanvas.height
////
        legendCanvas.width = (window.innerWidth / 5)
        legendCanvas.height = window.innerHeight - footerAndHeaderSize


        val cWidth = window.innerWidth / 5
        val cHeight = window.innerHeight - footerAndHeaderSize

        console.log("$cWidth vs $pWidth")
//        legendCanvas.width = cWidth
//        legendCanvas.height = cHeight
        if (!initCall) {
            when{
                pWidth ==0 -> Unit
             //   pWidth.compareTo(cWidth) != 0 -> draw()
            }
       //     window.requestAnimationFrame(::adjustSize)

        }
    }

    private fun adjustSize(e: Double = 0.0) = adjustSize(e, false)

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

            val adjustToBottem = tileSize;
            val descriptionElement = (legendDescriptions[index] as HTMLElement)
            descriptionElement.style.top = "${yPositionOfTile + adjustToBottem}px"

            val centerDescription = boxSize / 2 - descriptionElement.offsetWidth / 2
            descriptionElement.style.left = "${centerDescription}px"

        }

    }

    private fun calculateTileSize() = (legendCanvas.height / (kindOfTiles.size)) / 2;
}