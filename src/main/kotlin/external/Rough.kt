package external

import org.w3c.dom.HTMLCanvasElement

@JsName("rough")
external object Rough {
    fun canvas(canvas: HTMLCanvasElement): RoughCanvas
}

external class RoughCanvas {

    //        js("roughCanvas.rectangle(15, 55, 40, 40, { fill: 'red' })")
    fun rectangle(x: Int, y: Int, width: Int, height: Int)

    fun rectangle(x: Int, y: Int, width: Int, height: Int, options: ShapeOptions)
}

data class ShapeOptions(val roughness:Double=1.0,
                        val bowing:Int=1,
                        val stroke:String="#000000",
                        val strokeWidth:Int=1,
                        val fill:String="White",
                        val fillStyle:String="hachure",
                        val fillWeight:Double=strokeWidth.toDouble()/2,
                        val hachureAngle:Int=-41,
                        val hachureGap:Int=strokeWidth*4,
                        val curveStepCount:Int=9,
                        val simplification:Double=0.0)
