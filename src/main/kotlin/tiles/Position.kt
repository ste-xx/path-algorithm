package tiles

data class Position(val x: Int, val y: Int){
    constructor(xy:Pair<Int,Int>):this(xy.first,xy.second)
}