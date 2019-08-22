import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TestNeighboursCalculation {

    @Test
    fun `dont count self` () {
        assertThat(liveNeighbours(5,5,setOf(Pair(5,5)))).isEqualTo(0)
    }

    @Test
    fun `one neighbour` () {
        assertThat(liveNeighbours(5,5,setOf(Pair(5,6)))).isEqualTo(1)
    }

    @Test
    fun `one neighbour and self` () {
        assertThat(liveNeighbours(5,5,setOf(5 to 5, Pair(5,6)))).isEqualTo(1)
    }

    @Test
    fun `eigth (all) neighbours and self` () {
        assertThat(liveNeighbours(5,5, ((4..6).flatMap { x-> (4..6).map{ y -> x to y}}).toSet())).isEqualTo(8)
    }
}
