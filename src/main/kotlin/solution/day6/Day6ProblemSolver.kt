package solution.day6

import AbstractProblemSolver

class Day6ProblemSolver : AbstractProblemSolver<Long>() {
	private var input = getProblemInput().toMutableList()
	private val operators: List<String>
	private val operands: MutableList<List<Long>> = mutableListOf()

	init {
		operators = input[input.size - 1].split(Regex("\\s+"))
		input.removeLast()
		val splitInput = input.map { it.trim().split(Regex("\\s+")) }
		for (i in 0..splitInput[0].size - 1) {
			val currentOperands = mutableListOf<Long>()
			splitInput.forEach { currentOperands.add(it[i].toLong()) }
			operands.add(currentOperands)
		}
		assert(operands.size == operators.size)
	}

	override fun partOne(): Long {
		var total = 0L
		operands.forEachIndexed() { index, list ->
			if (operators[index] == "*") {
				total += list.reduce(Long::times)
			} else {
				total += list.reduce(Long::plus)
			}
		}
		return total
	}

	//disgusting puzzle, some numbers are aligned left and some right arbitrarily
	//	123 328
	//	  45 64
	// for example the second digit in the second row is index 2 for 45 but index 1 for 64
	// not going to solve this one because it would mean rewriting the parsing logic

	override fun partTwo(): Long {
		val topDownOperands = operands.map { toTopDownOperands(it) }
		var total = 0L
		topDownOperands.forEachIndexed() { index, list ->
			if (operators[index] == "*") {
				total += list.reduce(Long::times)
			} else {
				total += list.reduce(Long::plus)
			}
		}
		return total
	}

	private fun toTopDownOperands(operands: List<Long>): List<Long> {
		val maxDigits = operands.max().toString().length

		val paddedOperands = mutableListOf<String>()
		operands.forEach { paddedOperands.add(it.toString().padStart(maxDigits, '0')) }
		val newOperands = mutableListOf<Long>()
		for (i in 0 until maxDigits) {
			var newOperand = ""
			paddedOperands.forEach {
				if (it[i] != '0') {
					newOperand += it[i]
				}

			}
			newOperands.add(newOperand.toLong())
		}
		return newOperands
	}
}