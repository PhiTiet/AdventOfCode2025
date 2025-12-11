package solution.day5

import AbstractProblemSolver

class Day5ProblemSolver : AbstractProblemSolver<Long>() {
	private val input = getProblemInput("\n\n")
	val ranges = input[0].split("\n").map { LongRange(it.split("-")[0].toLong(), it.split("-")[1].toLong()) }
	val ingredients = input[1].split("\n").map { it.toLong() }
	override fun partOne(): Long {
		return ingredients.filter { ingredient ->
			ranges.any { it.contains(ingredient) }
		}.size.toLong()
	}

	override fun partTwo(): Long {
		//		return ranges.map { it.toSet() }.flatten().toSet().size.toLong()
		//		too much memory :(
		val merged = getMergedRanges(ranges)
		return merged.sumOf { (it.last() - it.first) + 1 }
	}

	private fun getMergedRanges(ranges: List<LongRange>): List<LongRange> {
		if (ranges.isEmpty()) return emptyList()

		val sorted = ranges.sortedWith(compareBy({ it.first }, { it.last }))
		val merged = mutableListOf<LongRange>()

		var currentStart = sorted[0].first
		var currentEnd = sorted[0].last

		for (i in 1 until sorted.size) {
			val range = sorted[i]

			if (range.first <= currentEnd + 1) {
				currentEnd = maxOf(currentEnd, range.last)
			} else {
				merged.add(currentStart..currentEnd)
				currentStart = range.first
				currentEnd = range.last
			}
		}

		merged.add(currentStart..currentEnd)
		return merged
	}
}