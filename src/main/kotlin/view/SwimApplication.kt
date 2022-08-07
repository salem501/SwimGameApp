package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

/**
 * a class to manage the scenes
 * @property rootService links all layers
 * @property scoreBoardScene scene to show the leader board
 * @property swimGameScene scene to play the game
 * @property playerCountScene scene to select the number of players
 * @property playerNameScene scene to give the players name
 * @property mainMenuScene scene to show the first scene after running the game
 */
class SwimApplication:BoardGameApplication("Swim Game"),Refreshable {
    private val rootService = RootService()

    /**
     * the replay button is set up to show the main menu when it is clicked
     */

    private val scoreBoardScene = ScoreBoardScene(rootService).apply {
        replayButton.onMouseClicked = {

            this@SwimApplication.showGameScene(mainMenuScene)
        }
    }

    private val swimGameScene = SwimGameScene(rootService)

    /**
     * the buttonTwoPlayers is set up to show the player name scene
     * and remove all the old components in case the game is restarted and there still
     * old components after that it starts a game with the number of players selected
     * the same foe the the other buttons
     */

    private val playerCountScene = PlayerCountScene(rootService).apply {

        buttonTwoPlayers.onMouseClicked = {
            this@SwimApplication.showGameScene(playerNameScene)
            playerNameScene.removeComponents(playerNameScene.p3Label,playerNameScene.textField3,
                playerNameScene.p4Label,playerNameScene.textField4)
            rootService.gameService.startNewGame(2)
        }
        buttonThreePlayers.onMouseClicked = {
            this@SwimApplication.showGameScene(playerNameScene)
            rootService.gameService.startNewGame(3)
            playerNameScene.removeComponents(playerNameScene.p3Label,playerNameScene.textField3,
                playerNameScene.p4Label,playerNameScene.textField4)
            playerNameScene.addComponents(playerNameScene.p3Label,playerNameScene.textField3)
        }
        buttonFourPlayers.onMouseClicked = {
            this@SwimApplication.showGameScene(playerNameScene)
            rootService.gameService.startNewGame(4)
            playerNameScene.removeComponents(playerNameScene.p3Label,playerNameScene.textField3,
                playerNameScene.p4Label,playerNameScene.textField4)
            playerNameScene.addComponents(playerNameScene.p3Label,playerNameScene.textField3,
                playerNameScene.p4Label,playerNameScene.textField4)
        }

    }

    /**
     * the button continueButton is set up to change the names in the entity layer
     * and show the swimGameScene
     */

    private val playerNameScene = PlayerNameScene(rootService).apply {
        continueButton.onMouseClicked = {
            when(rootService.currentGame!!.players.size){
                3-> rootService.gameService.configName(listOf(textField1.text,textField2.text,textField3.text))
                4-> rootService.gameService.configName(listOf(textField1.text,textField2.text,textField3.text,
                    textField4.text))
                else -> rootService.gameService.configName(listOf(textField1.text,textField2.text))
            }
            this@SwimApplication.showGameScene(swimGameScene)
        }
    }

    /**
     * the startButton is set up to show the playerCountScene after being clicked
     */
    private val mainMenuScene = MainMenuScene(rootService).apply {
        refreshAfterRestart()
        startButton.onMouseClicked = {this@SwimApplication.showGameScene(playerCountScene)}
    }


    /**
     * calls the score board scene after the game scene is ended
     */

    override fun refreshAfterEndGame() {
        this.showGameScene(scoreBoardScene)
    }

    /**
     * when the game gets restarted the main menu scene will be called
     */

    override fun refreshAfterRestart() {
        this.showGameScene(mainMenuScene)

    }

    /**
     * adds the scenes that must be refreshed to the refreshables list
     * and shows the main menu
     */
    init {
        rootService.addRefreshables(
            this,
            swimGameScene,
            playerNameScene,
            playerCountScene,
            mainMenuScene,
            scoreBoardScene
        )

        this.showGameScene(mainMenuScene)
    }
}