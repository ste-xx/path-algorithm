package algorithm

class DepthFirstSearchFail {
//
//    interface IEntry {
//        suspend fun solve(): Entry
//        fun getTile(): Tile
//        fun getPrevious(): List<IEntry>
//
//    }
//
//    data class Entry(val solved: Boolean, val entry: IEntry, val steps: Int)
//
//    fun solve(board: Board) {
//
//        class Entry(val previous: List<IEntry>, val tile: Tile, var steps: Int = 0) : IEntry {
//            override fun getTile(): Tile = tile;
//            override fun getPrevious(): List<IEntry> = previous;
//
//            override suspend fun solve(): Entry {
//                delay(50)
//                console.log("current $tile")
//                val pTiles = previous.map { it.getTile() }
//
//                return when {
//                    this.tile is GoalTile -> Entry(true, this, steps)
//
////                    this.tile is VisitedTile -> Entry(false, this, steps)
//                    this.tile is StartTile || this.tile is WalkableTile -> {
//                        val neighbours = board.getNeighboursOn(this.tile::positions).toList()
//                        neighbours.filter { it is WalkableTile || it is GoalTile }
////                                .filter { !(it is VisitedTile) }
//                                .filterNot { pTiles.contains(it) }
//                                .map { Entry(previous+this, it, steps = this.steps + 1) }
//                                .map {
//                                    it.solve()
//                                }
//                                .fold(Entry(false, this, steps)) { p, c ->
//                                    when {
//                                        c.solved && !p.solved -> c
//                                        (c.solved && p.solved) -> when {
//                                            c.steps < p.steps -> c
//                                            else -> p
//                                        }
//                                        else -> p
//                                    }
//                                }
//                    }
//                    else -> Entry(false, this, steps)
//                }
////                return Entry(false, this)
//            }
//
//        }
//
//
//
//        launch {
//            delay(1000)
//            console.log("try solve")
//            val result = Entry(listOf(), board.findStart()).solve()
//            console.log(result.solved)
////
//            board.setPath(result.entry.getPrevious().map { it.getTile().positions })
//
////            var entry: IEntry? =
////            val path = mutableListOf(result.entry.getTile().positions)
////            while (entry?.getPrevious() != null) {
////                entry = entry.getPrevious();
////                path.add(entry!!.getTile().positions)
////            }
////            board.setPath(path)
//        }
//
////
////        fun findRecursive(neighbourTiles: NeighbourTiles){
////            neighbourTiles.toList().filter {  it is EmptyTile}.forEach {
////                window.setTimeout({findRecursive(board.getNeighboursOn(it::positions))}, 1000)
////
////            }
////        }
////        findRecursive(board.getNeighboursOn(board.findStart()::positions));
//
//    }


}