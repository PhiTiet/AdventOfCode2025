package solution.day4

import AbstractProblemSolver
import solution.day4.model.PaperGrid
import solution.day4.model.PaperGridElement

class Day4ProblemSolver: AbstractProblemSolver<Int>() {
	private val grid = PaperGrid(getProblemInput().map { it.trim().toCharArray().toList().map { a -> PaperGridElement(a.toString()) } })

	override fun partOne(): Int {
		return grid.getReachableCoordinates().size
	}

	override fun partTwo(): Int {
		val removed: MutableSet<Pair<Int, Int>> = mutableSetOf()
		do{
			val reachable = grid.getReachableCoordinates()
			removed.addAll(reachable)
			reachable.forEach { grid.get(it.first, it.second).symbol = "." }
		}
		while (reachable.isNotEmpty())

		return removed.size
	}
}