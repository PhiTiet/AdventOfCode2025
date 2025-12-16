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
		return 0
	}

	override fun partTwo(): Long {
		return 0
	}
}