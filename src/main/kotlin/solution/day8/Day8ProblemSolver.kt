package solution.day8

import AbstractProblemSolver
import solution.day8.model.JunctionBox
import kotlin.math.pow
import kotlin.math.sqrt

class Day8ProblemSolver : AbstractProblemSolver<Long>() {
	private val junctionBoxes = getProblemInput().mapIndexed { index, line ->
		val (x, y, z) = line.split(",").map { it.toInt() }
		JunctionBox(x = x, y = y, z = z, id = index.toString())
	}
	private var distanceMap: MutableMap<Pair<JunctionBox, JunctionBox>, Double> = mutableMapOf()
	private var sortedBoxPairs: List<Pair<Pair<JunctionBox, JunctionBox>, Double>> = listOf()

	init {
		for (x in junctionBoxes) {
			for (y in junctionBoxes) {
				if (x == y || distanceMap.containsKey(Pair(x, y)) || distanceMap.containsKey(Pair(y, x))) {
					continue
				}
				val distance = distanceBetween(x, y)
				distanceMap[Pair(x, y)] = distance
			}
		}
		sortedBoxPairs = distanceMap.toList().sortedBy { it.second }
	}

	override fun partOne(): Long {
		var connections: MutableSet<MutableSet<JunctionBox>> = mutableSetOf()
		for (x in 0 until 1000) {
			val (box1, box2) = sortedBoxPairs[x].first
			val presentInCurrentConnections = connections.filter {
				it.contains(box1) || it.contains(box2)
			}
			if (presentInCurrentConnections.isEmpty()) {
				connections.add(mutableSetOf(box1, box2))
			} else if (presentInCurrentConnections.size == 1) {
				presentInCurrentConnections[0].add(box1)
				presentInCurrentConnections[0].add(box2)

			} else if (presentInCurrentConnections.size == 2) {
				val firstSet = presentInCurrentConnections[0]
				val secondSet = presentInCurrentConnections[1]
				connections = connections.filter { !it.containsAll(firstSet) && !it.containsAll(secondSet) }.toMutableSet()
				connections.add(firstSet.union(secondSet).toMutableSet())
			} else {
				error { "More than 2 connections found for boxes ${box1.id} and ${box2.id}" }
			}
		}
		for (box in junctionBoxes) {
			if (connections.none { it.contains(box) }) {
				connections.add(mutableSetOf(box))
			}
		}

		val connectionsListSortedBySizeDescending = connections.sortedByDescending { it.size }
		return connectionsListSortedBySizeDescending.map { it.size.toLong() }.subList(0, 3).reduce(Long::times)
	}

	override fun partTwo(): Long {
		var connections: MutableSet<MutableSet<JunctionBox>> = mutableSetOf()
		var finalConnectingPair: Pair<JunctionBox, JunctionBox>? = null
		for (x in 0 until Int.MAX_VALUE) {
			val (box1, box2) = sortedBoxPairs[x].first
			val presentInCurrentConnections = connections.filter {
				it.contains(box1) || it.contains(box2)
			}
			if (presentInCurrentConnections.isEmpty()) {
				connections.add(mutableSetOf(box1, box2))
			} else if (presentInCurrentConnections.size == 1) {
				if (!presentInCurrentConnections[0].contains(box1) && !presentInCurrentConnections[0].contains(box2)) {
					continue
				} else {
					presentInCurrentConnections[0].add(box1)
					presentInCurrentConnections[0].add(box2)
				}

			} else if (presentInCurrentConnections.size == 2) {
				if (connections.size == 2 && connections.flatten().size == junctionBoxes.size) {
					finalConnectingPair = Pair(box1, box2)
					break
				}
				val firstSet = presentInCurrentConnections[0]
				val secondSet = presentInCurrentConnections[1]
				connections = connections.filter { !it.containsAll(firstSet) && !it.containsAll(secondSet) }.toMutableSet()
				connections.add(firstSet.union(secondSet).toMutableSet())
			} else {
				error { "More than 2 connections found for boxes ${box1.id} and ${box2.id}" }
			}
		}
		return finalConnectingPair!!.first.x.toLong() * finalConnectingPair.second.x.toLong()
	}

	private fun distanceBetween(box1: JunctionBox, box2: JunctionBox): Double {
		val dx = (box1.x - box2.x).toDouble()
		val dy = (box1.y - box2.y).toDouble()
		val dz = (box1.z - box2.z).toDouble()
		return sqrt(dx.pow(2) + dy.pow(2) + dz.pow(2))
	}
}