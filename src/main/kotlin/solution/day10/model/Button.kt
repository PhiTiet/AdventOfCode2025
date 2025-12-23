package solution.day10.model

data class Button(
    val id: Int,
    val toggles: List<Boolean>
) {
    fun apply(state: List<Boolean>): List<Boolean> {
        return state.mapIndexed { index, value ->
            if (toggles[index]) !value else value
        }
    }
    fun applyJoltage(state: List<Int>): List<Int> {
        return state.mapIndexed { index, value ->
            if (toggles[index]) (value + 1) else value
        }
    }

    companion object {
        fun fromIndexes(id: Int, indexes: List<Int>, size: Int): Button {
            return Button(id, List(size) { i -> i in indexes })
        }
    }
}


