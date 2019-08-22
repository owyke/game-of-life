data class Grid(val height: Int, val width: Int, private val onToogle: (x: Int, y: Int) -> Unit) {
    private val state = mutableSetOf<Pair<Int, Int>>()

    init {
        assert(height > 0 && width > 0, { "Height and width must be gt zero." })
    }

    fun alive(x: Int, y: Int) = state.contains(Pair(x, y))


    fun toggle(x: Int, y: Int) {
        if (state.contains(Pair(x, y))) {
            state.remove(Pair(x, y))
        } else {
            state.add(Pair(x, y))
        }

        onToogle(x, y)
    }

    fun calcNext() {
        val shouldToggle = mutableListOf<Pair<Int, Int>>()
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                val neighbours = liveNeighbours(x, y, state)
                if (alive(x, y) && (neighbours < 2 || neighbours > 3)) {
                    shouldToggle.add(Pair(x, y))
                } else if (!alive(x, y) && neighbours == 3) {
                    shouldToggle.add(Pair(x, y))
                }
            }
        }
        shouldToggle.forEach{(x,y) ->toggle(x,y)}
    }
}

fun liveNeighbours(x: Int, y: Int, grid: Set<Pair<Int, Int>>) =
    (x - 1..x + 1).map { xi ->
        (y - 1..y + 1).map { yi ->
            if (grid.contains(Pair(xi, yi)) && !(x == xi && y == yi)) 1 else 0
        }.sum()
    }.sum()

