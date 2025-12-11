package solution.model.grid

abstract class AbstractGrid<E : AbstractGridElement>(protected val elements: List<List<E>>) {
    val size: Int = elements.size
    val sizeRange: IntRange = 0 until size

    init {
        isValidGrid(elements)
    }

    private fun isValidGrid(grid: List<List<E>>) {
        require(grid.isNotEmpty()) { "Grid must not be empty" }
        require(grid.all { row -> row.size == grid.size }) { "Grid must be square)" }
    }

    open operator fun get(y: Int, x: Int): E {
        return get(y, x) { it }
    }
    open operator fun get(coords: Pair<Int, Int>): E {
        return get(coords.first, coords.second) { it }
    }

    open operator fun <T> get(y: Int, x: Int, transform: (E) -> T): T {
        require((y in sizeRange) and (x in sizeRange)) { "Coordinates out of bounds" }
        return transform.invoke(elements[y][x])
    }

    fun firstIndexWhere(predicate: (E) -> Boolean): Pair<Int, Int> {
        for (y in sizeRange) {
            for (x in sizeRange) {
                if (predicate(elements[y][x])) {
                    return Pair(y, x)
                }
            }
        }
        throw IllegalStateException("no index found matching predicate")
    }

    fun allIndexesWhere(predicate: (E) -> Boolean):  List<Pair<Int,Int>> {
        val ret: MutableList<Pair<Int,Int>> = mutableListOf()
        for (y in sizeRange) {
            for (x in sizeRange) {
                if (predicate(elements[y][x])) {
                    ret.add(Pair(y, x))
                }
            }
        }
        return ret.toList()
    }

    fun count(predicate: (E) -> Boolean): Int {
        return allIndexesWhere { predicate.invoke(it) }.count()
    }

    fun isInRange(coords: Pair<Int, Int>): Boolean {
        return coords.first in sizeRange && coords.second in sizeRange
    }

    fun print(printSymbol: String = "@", predicate: (E) -> Boolean) {
        for (y in sizeRange) {
            for (x in sizeRange) {
                print(if (predicate(elements[y][x])) printSymbol else elements[y][x].symbol)
            }
            println()
        }
    }

    fun display() {
        elements.forEach { row ->
            println(row.map { e -> e.symbol })
        }
    }

    fun forEach(action: (E) -> Unit) {
        elements.forEach { row ->
            row.forEach { element ->
                action(element)
            }
        }
    }
}