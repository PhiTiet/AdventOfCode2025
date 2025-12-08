package solution.day3

import AbstractProblemSolver

class Day3ProblemSolver : AbstractProblemSolver<Long>() {
	private val input = getProblemInput().map { it.toList().map { it.digitToInt() } }

	override fun partOne(): Long {
		return input.sumOf { findHighestJoltageWithConstraint(it, 2) }
	}

	private fun findHighestJoltageWithConstraint(battery: List<Int>, sizeConstraint: Int): Long {
		var highest = ""
		var constraint = sizeConstraint
		var batteryCopy = battery
		while (constraint > 0) {
			val temp = batteryCopy.dropLast(constraint - 1)
			val firstHighestIndex = temp.indexOf(temp.maxOrNull()!!)
			highest += temp[firstHighestIndex].toString()
			batteryCopy = batteryCopy.subList(firstHighestIndex + 1, batteryCopy.size)
			constraint--
		}
		return highest.toLong()
	}

	override fun partTwo(): Long {
		return input.sumOf { findHighestJoltageWithConstraint(it, 12) }
	}
}