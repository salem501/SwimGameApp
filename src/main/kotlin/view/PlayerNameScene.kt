package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * a scene to type the players names
 * @property label label for the title
 * @property p1Label label for player1
 * @property p2Label label for player2
 * @property p3Label label for player3
 * @property p4Label label for player4
 * @property textField1 text field for typing player1's name
 * @property textField2 text field for typing player2's name
 * @property textField3 text field for typing player3's name
 * @property textField4 text field for typing player4's name
 * @property continueButton button to change to the game scene
 */

class PlayerNameScene(rootService: RootService) : Refreshable, BoardGameScene(720,480){
    private val label = Label(
        height = 50,
        width = 720,
        posX = 0,
        posY = 100,
        text = "Give names please !",
        font = Font(size = 40, color = Color.YELLOW)
    )

    private val p1Label = Label(
        height = 20,
        width = 300,
        posX = 0,
        posY = 210,
        text = "Player1",
        font = Font(size = 20, color = Color.WHITE)
    )
    private val p2Label = Label(
        height = 20,
        width = 300,
        posX = 0,
        posY = 260,
        text = "Player2",
        font = Font(size = 20, color = Color.WHITE)
    )
    val p3Label = Label(
        height = 20,
        width = 300,
        posX = 0,
        posY = 310,
        text = "Player3",
        font = Font(size = 20, color = Color.WHITE)
    )
    val p4Label = Label(
        height = 20,
        width = 300,
        posX = 0,
        posY = 360,
        text = "Player4",
        font = Font(size = 20, color = Color.WHITE)
    )

    val textField1 = TextField(
        height = 20,
        width = 200,
        posX = 350,
        posY = 210,
        prompt = "Player1's name"
    )

    val textField2 = TextField(
        height = 20,
        width = 200,
        posX = 350,
        posY = 260,
        prompt = "Player2's name"
    )

    val textField3 = TextField(
        height = 20,
        width = 200,
        posX = 350,
        posY = 310,
        prompt = "Player3's name"
    )

    val textField4 = TextField(
        height = 20,
        width = 200,
        posX = 350,
        posY = 360,
        prompt = "Player4's name"
    )

    val continueButton = Button(
        height = 30,
        width = 100,
        posX = 600,
        posY = 435,
        text = "continue",
        visual = ColorVisual(136, 221, 136)
    )

    /**
     * set background and add components
     */
    init {
        background = ImageVisual(path = "background2.jpg")
        addComponents(label,p1Label,p2Label,textField1,textField2,continueButton)
    }
}