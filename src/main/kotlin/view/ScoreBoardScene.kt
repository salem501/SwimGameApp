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
 * a scene to show the leader board after game finish
 *
 * @property gameOverLabel title
 * @property winner1Label label for the player with rank 1
 * @property winner2Label label for the player with rank 2
 * @property winner3Label label for the player with rank 3
 * @property winner4Label label for the player with rank 4
 * @property replayButton a button to start a new game
 */
class ScoreBoardScene(private val rootService: RootService):Refreshable, BoardGameScene(720,480) {
    private val gameOverLabel = Label(
        posY = 0,
        posX = 0,
        width = 720,
        height = 100,
        text = "Game Over !",
        font = Font(size = 100, color = Color.CYAN)
    )
     private val winner1Label = Label(
        posY = 120,
        posX = 0,
        width = 720,
        height = 80,
        text = "Test",
        font = Font(size = 50, color = Color.YELLOW)
    )
    private val winner2Label = Label(
        posY = 200,
        posX = 0,
        width = 720,
        height = 70,
        text = "Test",
        font = Font(size = 40, color = Color.YELLOW)
    )
    private val winner3Label = Label(
        posY = 270,
        posX = 0,
        width = 720,
        height = 60,
        text = "Test",
        font = Font(size = 30, color = Color.YELLOW)
    )
    private val winner4Label = Label(
        posY =  330,
        posX = 0,
        width = 720,
        height = 70,
        text = "Test",
        font = Font(size = 20, color = Color.YELLOW)
    )

    val replayButton = Button(
        width = 150,
        height = 50,
        posX = 540,
        posY = 400,
        text = "replay",
        font = Font(size = 20, color = Color.DARK_GRAY),
        visual = ColorVisual(color = Color.CYAN)
    )

    /**
     * the players will be sorted based on their points
     * and based on the players number the labels will be shown
     */
    override fun refreshAfterEndGame() {

        val players = rootService.currentGame!!.players
        players.sortByDescending { players -> players.score }
        when(players.size){
            3-> {
                removeComponents(replayButton,winner4Label,winner3Label,winner2Label,winner1Label,gameOverLabel)
                winner1Label.text = "1 :  " + players[0].playerName  + " : " + players[0].score + " points"
                winner2Label.text = "2 :  " + players[1].playerName  + " : " + players[1].score + " points"
                winner3Label.text = "3 :  " + players[2].playerName  + " : " + players[2].score + " points"
                addComponents(winner1Label,winner2Label,winner3Label,gameOverLabel,replayButton)
            }
            4->{
                removeComponents(replayButton,winner4Label,winner3Label,winner2Label,winner1Label,gameOverLabel)
                winner1Label.text = "1 :  " + players[0].playerName  + " : " + players[0].score + " points"
                winner2Label.text = "2 :  " + players[1].playerName  + " : " + players[1].score + " points"
                winner3Label.text = "3 :  " + players[2].playerName  + " : " + players[2].score + " points"
                winner4Label.text = "4 :  " + players[3].playerName  + " : " + players[3].score + " points"
                addComponents(winner1Label,winner2Label,winner3Label,winner4Label,gameOverLabel,replayButton)
            }
            else -> {
                removeComponents(replayButton,winner4Label,winner3Label,winner2Label,winner1Label,gameOverLabel)
                winner1Label.text = "1 :  " + players[0].playerName  + " : " + players[0].score + " points"
                winner2Label.text = "2 :  " + players[1].playerName  + " : " + players[1].score + " points"
                addComponents(winner1Label,winner2Label,gameOverLabel,replayButton)
            }

        }

    }

    /**
     * set background
     */
    init {

        background = ImageVisual(path = "background.jpg")
    }
}