package algorithm

import board.Board
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import tiles.*

class DepthFirstSearch(override val name: String="DFS[Fail]") : PathFindingAlgorithm {


    interface IEntry {
        suspend fun solve(): EntryData
        fun getTile(): Tile
        fun getPrevious(): List<IEntry>

    }

    data class EntryData(val solved: Boolean, val entry: IEntry, val steps: Int)

    override suspend fun solve(board: Board, drawWait: Int): PathFindingAlgorithm.PathFindingResult {

        class Entry(val previous: List<IEntry>, val tile: Tile, var steps: Int = 0) : IEntry {
            override fun getTile(): Tile = tile
            override fun getPrevious(): List<IEntry> = previous

            override suspend fun solve(): EntryData {
                delay(drawWait)
                console.log("current $tile")
                val pTiles = previous.map { it.getTile() }

                return when {
                    this.tile is GoalTile -> EntryData(true, this, steps)

//                    this.tile is VisitedTile -> Entry(false, this, steps)
                    this.tile is StartTile || this.tile is WalkableTile -> {
                        val neighbours = board.getNeighboursOn(this.tile.position).toList()
                        neighbours.filter { it is WalkableTile || it is GoalTile }
                                .filterNot { it is VisitedTile }
                                .filterNot(pTiles::contains)
                                .map { Entry(previous+this, it, steps = this.steps + 1) }
                                .map {
                                    it.solve()
                                }
                                .fold(EntryData(false, this, steps)) { p, c ->
                                    when {
                                        c.solved && !p.solved -> c
                                        (c.solved && p.solved) -> when {
                                            c.steps < p.steps -> c
                                            else -> p
                                        }
                                        else -> p
                                    }
                                }
                    }
                    else -> EntryData(false, this, steps)
                }
//                return Entry(false, this)
            }

        }



        launch {
            console.log("try solve")
            val result = Entry(listOf(), board.findStart()).solve()
            console.log(result.solved)
//
            board.setPath(result.entry.getPrevious().map { Path(it.getTile().position) }.reduce{p,c -> p+c})

//            var entry: IEntry? =
//            val path = mutableListOf(result.entry.getTile().positions)
//            while (entry?.getPrevious() != null) {
//                entry = entry.getPrevious();
//                path.add(entry!!.getTile().positions)
//            }
//            board.setPath(path)
        }

//
//        fun findRecursive(neighbourTiles: NeighbourTiles){
//            neighbourTiles.toList().filter {  it is EmptyTile}.forEach {
//                window.setTimeout({findRecursive(board.getNeighboursOn(it::positions))}, 1000)
//
//            }
//        }
//        findRecursive(board.getNeighboursOn(board.findStart()::positions));
        return PathFindingAlgorithm.PathFindingResult(false, Path())
    }


}