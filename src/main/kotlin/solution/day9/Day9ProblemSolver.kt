package solution.day9

import AbstractProblemSolver
import solution.day9.model.RedTile

class Day9ProblemSolver : AbstractProblemSolver<Long>() {
	private val tiles = getProblemInput()
		.map {
			it.split(",").map { a -> a.toLong() }
		}
		.map{ RedTile(x =it[0], y =it[1])}

	override fun partOne(): Long {
		val rectangleSizes = mutableSetOf<Long>()
		for (x in tiles) {
			for (y in tiles) {
				if (x == y ) {
					continue
				}
				rectangleSizes.add(calculateRectangleArea(x, y))
			}
		}
		return rectangleSizes.max()
	}

	override fun partTwo(): Long {
		return 0
	}
	private fun calculateRectangleArea(tile1: RedTile, tile2: RedTile): Long {
		val length = kotlin.math.abs(tile1.x - tile2.x + 1)
		val width = kotlin.math.abs(tile1.y - tile2.y + 1)
		return length * width
	}

}