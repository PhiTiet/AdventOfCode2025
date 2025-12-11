package solution.day4.model

import solution.model.grid.AbstractGrid

class PaperGrid(elements: List<List<PaperGridElement>>) : AbstractGrid<PaperGridElement>(elements) {
	fun getAdjacentPapers(y: Int, x: Int): List<PaperGridElement> {
		val directions = listOf(
			Pair(-1, -1), // top-left
			Pair(-1, 0),  // top
			Pair(-1, 1),  // top-right
			Pair(0, -1),  // left
			Pair(0, 1),   // right
			Pair(1, -1),  // bottom-left
			Pair(1, 0),   // bottom
			Pair(1, 1)    // bottom-right
		)
		return directions.mapNotNull { (dy, dx) ->
			if (y + dy in sizeRange && x + dx in sizeRange) get(y + dy, x + dx) else null
		}
	}
	fun getReachableCoordinates(): List<Pair<Int, Int>> {
		val reachableCoords = mutableListOf<Pair<Int, Int>>()
		for (y in sizeRange) {
			for (x in sizeRange) {
				val adjacent = getAdjacentPapers(y, x)
				val current = get(y, x)
				if (current.symbol != "@") {
					continue
				}
				if (adjacent.filter { it.symbol == "@" }.count() < 4) {
					reachableCoords.add(Pair(y, x))
				}
			}
		}
		return reachableCoords.toList()
	}


}