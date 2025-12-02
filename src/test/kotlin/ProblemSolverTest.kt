import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import solution.day1.Day1ProblemSolver
import solution.day2.Day2ProblemSolver
import kotlin.test.assertEquals

class ProblemSolverTest {

	@Test
	fun testCurrent(){
		val solver = Day2ProblemSolver()
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