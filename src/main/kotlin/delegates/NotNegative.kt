package delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private data class Position(val x: Int, val y: Int) {
//        private var validateX: Int by NotNegative(0)
//        private var validateY: Int by NotNegative(0)
//
//        init {
//            validateX = x
//            validateY = y;
//        }
}

class NotNegative<T : Number>(defaultValue: T) : ReadWriteProperty<Any?, T> {
    private var value: T = defaultValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = when {
            value is Int && value < 0 -> throw IllegalArgumentException("Negative values are not supported.")
            value is Double && value < 0 -> throw IllegalArgumentException("Negative values are not supported.")
            value is Float && value < 0 -> throw IllegalArgumentException("Negative values are not supported.")
            else -> value
        }
    }
}