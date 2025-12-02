import client.AOCClient

abstract class AbstractProblemSolver<A : Number> : ProblemSolver<A> {

    protected fun getProblemInput(): List<String> {
        return getProblemInput("\n")
    }

    protected fun getProblemInput(delimiter: String): List<String> {
        return AOCClient().getInput(dayNumberFromClass(), delimiter)
    }

    private fun dayNumberFromClass(): Int {
        return (Regex("Day(\\d+)")
            .find(this.javaClass.simpleName)
            ?.groupValues?.get(1)?.toInt()
            ?: throw IllegalArgumentException("Day number not found"))
    }
}
