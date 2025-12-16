package solution.day7

import AbstractProblemSolver
import solution.day7.model.TaychonGrid
import solution.day7.model.TaychonGridElement

class Day7ProblemSolver : AbstractProblemSolver<Long>() {
	private val grid = TaychonGrid(getProblemInput().map { it.trim().toCharArray().toList().map { a -> TaychonGridElement(a.toString()) } })

	override fun partOne(): Long {
		var currentBeams: MutableList<Boolean> = MutableList(grid.widthRange.last + 1) { false }
		var nextBeams = MutableList(grid.widthRange.last + 1) { false }

		var split = 0L

		for (y in grid.heightRange) {
			for (x in grid.widthRange) {
				val element = grid[y, x]
				if (element.symbol == "S") {
					nextBeams[x] = true
					continue
				}
				if (!currentBeams[x]) {
					continue
				}

				if (element.symbol == "^") {
					assert(currentBeams[x])
					nextBeams[x] = false
					split++
					if (x > 0) {
						nextBeams[x - 1] = true
					}
					if (x < grid.widthRange.last) {
						nextBeams[x + 1] = true
					}
				} else {
					nextBeams[x] = true
				}
			}
			currentBeams = nextBeams
			nextBeams = MutableList(grid.widthRange.last + 1) { false }
		}
		return split
	}

	override fun partTwo(): Long {
		var currentBeams: MutableList<Long> = MutableList(grid.widthRange.last + 1) { 0L }
		var nextBeams = MutableList(grid.widthRange.last + 1) { 0L }
		for (y in grid.heightRange) {
			for (x in grid.widthRange) {
				val element = grid[y, x]
				if (element.symbol == "S") {
					nextBeams[x] = 1
					continue
				}
				if (currentBeams[x] == 0L) {
					continue
				}

				if (element.symbol == "^") {
					assert(currentBeams[x] != 0L)
					if (x > 0) {
						nextBeams[x - 1] += currentBeams[x]
					}
					if (x < grid.widthRange.last) {
						nextBeams[x + 1] += currentBeams[x]
					}
				} else {
					nextBeams[x] += currentBeams[x]
				}
			}
			currentBeams = nextBeams
			nextBeams = MutableList(grid.widthRange.last + 1) { 0L }
		}
		return currentBeams.sum()
	}
}