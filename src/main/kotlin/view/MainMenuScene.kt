package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color
import java.lang.System.exit
import kotlin.system.exitProcess

/**
 * the first scene after running the game
 * @property swimGameLabel a label for the title
 * @property developerLabel a label doe the name of the developer :)
 * @property startButton a button to go to the player number choosing scene
 * @property exitButton a button to close the game
 */
class MainMenuScene (rootService: RootService): Refreshable, BoardGameScene(720,480){
    private val swimGameLabel = Label(
        width = 600,
        height = 100,
        posY = 100,
        posX = 60,
        text = "Swim Game",
        font = Font(size = 100, fontStyle = Font.FontStyle.OBLIQUE, color = Color.YELLOW)

    )
    private val developerLabel = Label(
        width = 100,
        height = 30,
        posY = 450,
        posX = 620,
        text = "by Salem Slimi :)",
        font = Font(size = 10, fontStyle = Font.FontStyle.ITALIC, color = Color.WHITE)
    )
    val startButton = Button(
        width = 150,
        height = 50,
        posX = 285,
        posY = 300,
        text = "Start Game",
        visual = ColorVisual(136, 221, 136)
    )

    private val exitButton = Button(
        width = 150,
        height = 50,
        posX = 285,
        posY = 400,
        text = "Exit",
        visual = ColorVisual(221, 136, 136)
    ).apply { onMouseClicked = { exitProcess(0) } }

    /**
     * set the background and add the components
     */
    init {
        background = ImageVisual(path = "background2.jpg")
        addComponents(swimGameLabel,startButton,exitButton,developerLabel)
    }
}