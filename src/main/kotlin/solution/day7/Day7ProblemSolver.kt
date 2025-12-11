package solution.day7

import AbstractProblemSolver
import solution.day7.model.TaychonGrid
import solution.day7.model.TaychonGridElement

class Day7ProblemSolver : AbstractProblemSolver<Long>() {
	private val grid = TaychonGrid(getProblemInput().map { it.trim().toCharArray().toList().map { a -> TaychonGridElement(a.toString()) } })

	override fun partOne(): Long {
		grid.display()
		return 0
	}

	override fun partTwo(): Long {
		return 0
	}
}