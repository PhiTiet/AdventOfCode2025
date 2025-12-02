package solution.day2

import AbstractProblemSolver

class Day2ProblemSolver : AbstractProblemSolver<Long>() {
	val input = getProblemInput().get(0).split(",").map { line ->
		val (from, to) = line.split("-")
		IdRange(from.toLong(), to.toLong())}

	override fun partOne(): Long {
		var totalInvalid = 0L
		input.forEach {
			for (id in it.from..it.to) {
				if (!isValidPart1(id)) {
					totalInvalid+= id
				}
			}
		}
		return totalInvalid
	}

	private fun isValidPart1(id: Long) : Boolean {
		val stringify = id.toString()
		if (stringify.length % 2 != 0) {
			return true
		}
		val mid = stringify.length / 2
		return stringify.substring(0, mid) != stringify.substring(mid)
	}

	override fun partTwo(): Long {
		var totalInvalid = 0L
		input.forEach {
			for (id in it.from..it.to) {
				if (!isValidPart2(id)) {
					totalInvalid+= id
				}
			}
		}
		return totalInvalid
	}
	private fun isValidPart2(id: Long) : Boolean {
		val stringify = id.toString()
		val mid = stringify.length / 2
		for( i in 1 until mid + 1){
			if (stringify.length % i != 0){
				continue
			}
			if (stringify.substring(0, i).repeat(stringify.length / i) == stringify){
				return false
			}
		}
		return true
	}
}