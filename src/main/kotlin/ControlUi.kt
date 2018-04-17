import algorithm.PathFindingAlgorithm
import board.Board

interface ControlUi{
    fun draw(algorithms: List<PathFindingAlgorithm>, board: Board)
}