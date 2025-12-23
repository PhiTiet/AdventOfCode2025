package solution.day10.model

data class LightMachine(
	val desiredInitialStage: List<Boolean>,
	val buttons: List<Button>,
	val desiredJoltage: List<Int>
) {
	fun minimalButtonPressesToDesiredState(): Long {
		var newSearch = mutableListOf<Pair<List<Int>, List<Boolean>>>()
		val initial = List(desiredInitialStage.size) { false }
		var currentSearch = buttons.map { Pair(listOf(it.id), initial) }.toMutableList()
		var presses = 0L
		val alreadyReached = mutableSetOf(initial)
		while (true) {
			presses++
			currentSearch.forEach {
				val newState = buttons[it.first.last()].apply(it.second)
				if (newState == desiredInitialStage) {
					return presses
				} else if (newState == initial || alreadyReached.contains(newState)) {
					//skip
				} else {
					alreadyReached.add(newState)
					buttons.forEach { button ->
						newSearch.add(Pair(it.first + button.id, newState))
					}
				}
			}
			currentSearch = newSearch
			newSearch = mutableListOf()
		}
		return presses
	}

	fun minimalButtonPressesToDesiredJoltage(): Long {
		var newSearch = mutableListOf<Pair<MutableList<Int>, List<Int>>>()
		val initial = List(desiredJoltage.size) { 0 }
		val buttonPresses = MutableList(buttons.size) { 0 }
		var currentSearch = listOf(Pair(buttonPresses, initial))
		var presses = 0
		val alreadyReached : MutableMap<List<Int>, Int> = mutableMapOf()
		val alreadyReachedButtons = mutableSetOf(buttonPresses)

		while (true) {
			presses++
			println(currentSearch.size)
			for (current in currentSearch) {
				for ((index, button) in buttons.withIndex()) {
					val newState = button.applyJoltage(current.second)
					if (newState == desiredJoltage) {
						return presses.toLong()
					}

					if (invalidState(newState, desiredJoltage)) {
						continue
					}

					val prevPresses = alreadyReached[newState]
					if (prevPresses != null && prevPresses <= presses) {
						continue
					}

					alreadyReached[newState] = presses
					val copy = current.first.toMutableList()
					copy[index] = copy[index] + 1

					if (alreadyReachedButtons.contains(copy)) {
						continue
					}

					newSearch.add(Pair(copy, newState))
					alreadyReachedButtons.add(copy)
				}

			}

			newSearch = newSearch.distinctBy { it.first }.toMutableList()
			newSearch = newSearch.distinctBy { it.second }.toMutableList()

			currentSearch = newSearch
			newSearch = mutableListOf()
		}
		return presses.toLong()
	}

	private fun invalidState(state: List<Int>, desiredState: List<Int>): Boolean {
		state.forEachIndexed { index, value ->
			if (value > desiredState[index]) {
				return true
			}
		}
		return false
	}

	companion object {
		fun fromLine(line: String): LightMachine {
			val pattern = line.substringBefore("]").removePrefix("[")
			val desiredState = pattern.map { it == '#' }
			val groups = line.substringAfter("] ").substringBefore(" {")
				.split(" ")
				.map { it.removeSurrounding("(", ")").split(",").map { n -> n.toInt() } }
			val buttons = groups.mapIndexed { index, it -> Button.fromIndexes(index, it, desiredState.size) }
			val desiredJoltage = line.substringAfter("{").removeSuffix("}")
				.split(",")
				.map { it.toInt() }
			return LightMachine(desiredState, buttons, desiredJoltage)
		}
	}
}

