
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import solution.day1.Day1ProblemSolver
import solution.day2.Day2ProblemSolver
import solution.day3.Day3ProblemSolver
import solution.day4.Day4ProblemSolver
import solution.day5.Day5ProblemSolver
import solution.day6.Day6ProblemSolver
import solution.day7.Day7ProblemSolver
import kotlin.test.assertEquals

class ProblemSolverTest {

	@Test
	fun testCurrent(){
		val solver = Day7ProblemSolver()
		println(solver.partOne())
		println(solver.partTwo())
	}
	@Test
	fun dayOne() {
		verify(Day1ProblemSolver(), answerOne = 1100, answerTwo = 6358)
	}

	@Test
	fun dayTwo() {
		verify(Day2ProblemSolver(), answerOne = 35367539282L, answerTwo = 45814076230)
	}

	@Test
	fun dayThree() {
		verify(Day3ProblemSolver(), answerOne = 16927L, answerTwo = 167384358365132L)
	}
	@Test
	fun dayFour() {
		verify(Day4ProblemSolver(), answerOne = 1437, answerTwo = 8765)
	}
	@Test
	fun dayFive() {
		verify(Day5ProblemSolver(), answerOne = 874, answerTwo = 348548952146313)
	}
	@Test
	fun daySix() {
		verify(Day6ProblemSolver(), answerOne = 7644505810277, answerTwo = 12866637741218) //12841228084455
	}
	@Test
	fun daySeven() {
		verify(Day7ProblemSolver(), answerOne = 1592, answerTwo = 17921968177009)
	}

	private fun <A : Number> verify(
		solver: AbstractProblemSolver<A>,
		answerOne: A,
		answerTwo: A
	) {
		assertAll(
			"Verify both parts",
			{ assertEquals(answerOne, solver.partOne()) },
			{ assertEquals(answerTwo, solver.partTwo()) }
		)
	}

}