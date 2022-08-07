package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * a scene for choosing the players number
 * @property label contains a Title "Number of Players"
 * @property buttonTwoPlayers a button to start a game with two players
 * @property buttonThreePlayers a button to start a game with three players
 * @property buttonFourPlayers a button to start a game with four players
 */
class PlayerCountScene (rootService: RootService): Refreshable, BoardGameScene(720,480){
    private val label = Label(
        width = 720,
        height = 100,
        posX = 0,
        posY = 100,
        text = "Number of Players",
        font = Font(size = 65, fontStyle = Font.FontStyle.OBLIQUE, color = Color.YELLOW )
    )
    val buttonTwoPlayers = Button(
        width = 100,
        height = 100,
        posY = 220,
        posX = 160,
        text = "2",
        font = Font(size = 20)
    )
    val buttonThreePlayers = Button(
        width = 100,
        height = 100,
        posY = 220,
        posX = 310,
        text = "3",
        font = Font(size = 20)
    )

    val buttonFourPlayers = Button(
        width = 100,
        height = 100,
        posY = 220,
        posX = 460,
        text = "4",
        font = Font(size = 20)
    )


    /**
     * set the background and add the components
     */
    init {
        background = ImageVisual(path = "background2.jpg")
        addComponents(buttonTwoPlayers,buttonThreePlayers,buttonFourPlayers,label)

    }
}