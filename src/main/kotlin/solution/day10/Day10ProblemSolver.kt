package solution.day10

import AbstractProblemSolver
import solution.day10.model.LightMachine

class Day10ProblemSolver : AbstractProblemSolver<Long>(){

	private val machines = getProblemInput().map { LightMachine.fromLine(it) }
	override fun partOne(): Long {
		return machines.sumOf { it.minimalButtonPressesToDesiredState() }
	}

	override fun partTwo(): Long {
		return machines.sumOf { it.minimalButtonPressesToDesiredJoltage() }
	}
}