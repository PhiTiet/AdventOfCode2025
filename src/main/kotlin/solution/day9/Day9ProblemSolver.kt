package solution.day9

import AbstractProblemSolver
import solution.day9.model.RedTile
import kotlin.math.abs

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
	private fun calculateRectangleArea(tile1: RedTile, tile2: RedTile): Long {
		return abs(tile1.x - tile2.x + 1) * abs(tile1.y - tile2.y + 1)
	}

	override fun partTwo(): Long {
		return 0
	}


}