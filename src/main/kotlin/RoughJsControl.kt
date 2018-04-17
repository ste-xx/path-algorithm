import algorithm.PathFindingAlgorithm
import board.Board
import external.Rough
import external.RoughCanvas
import external.ShapeOptions
import kotlin.browser.document
import kotlin.browser.window
import kotlinx.coroutines.experimental.launch
import org.w3c.dom.*


private const val OFFSET_LEFT_FOR_BUTTON: Int = 20
private const val OFFSET_TOP_FOR_BUTTON: Int = 45

private fun HTMLElement.createSibling(e: Element): Node = this.parentElement!!.insertBefore(e, this)

private fun Document.createSolveAlgorithmButton(algorithm: PathFindingAlgorithm, board: Board, index: Int): Element {
    val e = this.createElement("div") as HTMLElement
    e.classList.add("controlElement")
    e.style.top = "${OFFSET_TOP_FOR_BUTTON * (index + 1)}px"
    e.style.left = "${OFFSET_LEFT_FOR_BUTTON}px"
    e.innerHTML = "Solve with ${algorithm.name}"
    e.addEventListener("click", {
        launch {
            algorithm.solve(board)
        }
    })
    return e
}

private fun Document.createHeader(): Element {
    val e = this.createElement("div") as HTMLElement
    e.classList.add("controlElement")
    e.style.top = "10px"
    e.style.left = "${OFFSET_LEFT_FOR_BUTTON}px"
    e.innerHTML = "Control"
    return e
}

private fun HTMLCanvasElement.addSolveAlgorithmBtn(index: Int, pair: Pair<PathFindingAlgorithm, Board>) = addSolveAlgorithmBtn(index, pair.first, pair.second)
private fun HTMLCanvasElement.addSolveAlgorithmBtn(index: Int, algo: PathFindingAlgorithm, board: Board) =
        this.createSibling(document.createSolveAlgorithmButton(algo, board, index))

private fun HTMLCanvasElement.addHeader() = this.createSibling(document.createHeader());


private fun RoughCanvas.addBtnBoarderAndFill(index: Int, e: Element) =
        this.rectangle(OFFSET_LEFT_FOR_BUTTON, OFFSET_TOP_FOR_BUTTON * (index + 1), e.clientWidth, e.clientHeight, ShapeOptions(fill = "#00ff00"))

object RoughJsControl : ControlUi {

    private val control: RoughCanvas
    private val controlCanvas = document.getElementById("control") as HTMLCanvasElement

    init {
        control = Rough.canvas(controlCanvas)
        adjustSize()
    }

    private fun adjustSize() {
        controlCanvas.width = (window.innerWidth / 5)
        controlCanvas.height = window.innerHeight - FOOTER_AND_HEADER_SIZE
    }

    override fun draw(algorithms: List<PathFindingAlgorithm>, board: Board) {
        drawHeader()
        drawAlgorithmControls(algorithms, board)
        val width = document.querySelectorAll(".controlElement").asList()
                .map { it as Element }
                .map { it.clientWidth }
                .maxBy { it }

        control.rectangle(0, 0, width!! + OFFSET_LEFT_FOR_BUTTON * 2, controlCanvas.height)

    }

    private fun drawHeader()= controlCanvas.addHeader()

    private fun drawAlgorithmControls(algorithms: List<PathFindingAlgorithm>, board: Board) = algorithms
            .map { Pair(it, board) }
            .mapIndexed(controlCanvas::addSolveAlgorithmBtn)
            .map { it as Element }
            .forEachIndexed(control::addBtnBoarderAndFill)


}