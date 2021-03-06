package algorithm

class Queue<T>(vararg entries: T) {

    var items: MutableList<T> = entries.toMutableList()

    fun isEmpty(): Boolean = this.items.isEmpty()

    fun isNotEmpty(): Boolean = this.items.isNotEmpty()

    fun count(): Int = this.items.count()

    override fun toString() = this.items.toString()

    fun enqueue(element: T) {
        this.items.add(element)
    }

    fun enqueue(element: Iterable<T>) {
        this.items.addAll(element)
    }

    fun dequeue(): T? = when {
        this.isEmpty() -> null
        else -> this.items.removeAt(0)
    }

    fun peek(): T? = this.items[0]
}