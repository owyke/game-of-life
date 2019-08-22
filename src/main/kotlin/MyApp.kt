
import javafx.event.EventHandler
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Shape
import tornadofx.*

fun main() {
    launch<MyApp>()
}


class MyApp : App(MyView::class)

class MyView : View() {
    private val grid = Grid(100, 100, ::updateRect)

    private val gridView = gridView()
    override val root = borderpane {
        top = controls()
        center = gridView
    }



    private fun controls() =
        button("Next").apply {
            this.onMouseClicked = EventHandler { e ->
                grid.calcNext()

            }
        }


    private fun updateRect(x: Int, y: Int) {
        val shape = gridView.getChildList()?.get(y)?.getChildList()?.get(x) as Shape
        println(shape)
        shape.fill = toColor(grid.alive(x, y))
        println("Recolor $x, $y")
    }

    private fun gridView(): VBox {
        return vbox {
            (0..grid.height - 1).map { y ->
                hbox {
                    (0..grid.width - 1).map { x ->
                        rectangle {
                            fill = toColor(grid.alive(x, y))
                            width = 10.0
                            height = 10.0
                        }.apply {
                            this.onMousePressed = EventHandler { e ->
                                grid.toggle(x, y)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun toColor(alive: Boolean) =
        if (alive) Color.RED else Color.GRAY


}

