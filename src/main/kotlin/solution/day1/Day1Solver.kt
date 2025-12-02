package solution.day1

import AbstractProblemSolver

class Day1ProblemSolver : AbstractProblemSolver<Int>() {
	private val input = getProblemInput().map { line ->
		val (direction, distance) = Regex("([A-Z])(\\d+)").find(line)!!.destructured
		TurnInstruction(direction, distance.toInt())
	}

	override fun partOne(): Int {
		var timesStoppedAtZero = 0
		var rotation = 50

		input.forEach {
			if (it.turnDirection == "L") {
				rotation -= it.distance
			} else {
				rotation += it.distance
			}
			rotation %= 100
			if (rotation == 0) timesStoppedAtZero++
		}

		return timesStoppedAtZero
	}

	override fun partTwo(): Int {
		var passedZero = 0
		var rotation = 50
		//basic intuition : first check if the turn crosses(or stops at) zero as if it only had 2 digits.
		// so 921 left = check 21 , if it crosses zero +=1 then add the full turns
		input.forEach {
			val startRotation = rotation
			if (it.turnDirection == "L") {
				val withoutFullTurns = it.distance % 100
				if (rotation - withoutFullTurns <= 0) {
					passedZero++
					if (startRotation == 0) {
						passedZero--
					}
				}
				passedZero += (it.distance / 100)
				rotation -= it.distance
				rotation %= 100
				if (rotation < 0) {
					rotation += 100
				}
			} else {
				val withoutFullTurns = it.distance % 100
				if (rotation + withoutFullTurns >= 100) {
					passedZero++
				}
				passedZero += (it.distance / 100)
				rotation += it.distance
				rotation %= 100
			}
		}
		return passedZero
	}
}