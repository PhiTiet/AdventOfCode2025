import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import solution.day1.Day1ProblemSolver
import solution.day2.Day2ProblemSolver
import solution.day3.Day3ProblemSolver
import solution.day4.Day4ProblemSolver
import kotlin.test.assertEquals

class ProblemSolverTest {

	@Test
	fun testCurrent(){
		val solver = Day4ProblemSolver()
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