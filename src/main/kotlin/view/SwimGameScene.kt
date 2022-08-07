package view

import service.CardImageLoader
import service.RootService
import tools.aqua.bgw.components.container.LinearLayout
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * a scene for playing the game
 *
 * @property knockButton a button to do the knock action
 * @property passButton a button to do the pass action
 * @property swapOneButton a button to do the swap one action
 * @property swapAllButton a button to do the swap all action
 *
 * @property rightNameLabel a label to show the name of right player
 * @property leftNameLabel a label to show the name of left player
 * @property topNameLabel a label to show the name of top player
 * @property mainNameLabel a label to show the name of actual player
 *
 * @property rightActionLabel a label to show the last action of right player
 * @property leftActionLabel a label to show the last action of left player
 * @property topActionLabel a label to show the last action of top player
 * @property mainActionLabel a label to show the last action of actual player
 *
 * @property linearLayoutLeftPlayer a linear layout to create fake cards for the left player
 * @property linearLayoutRightPlayer a linear layout to create fake cards for the right player
 * @property linearLayoutTopPlayer a linear layout to create fake cards for the top player
 *
 * @property card1 the first card of the actual player
 * @property card2 the second card of the actual player
 * @property card3 the third card of the actual player
 *
 * @property poolCard1 the first card int the pool
 * @property poolCard2 the second card int the pool
 * @property poolCard3 the third card int the pool
 *
 * @property scoreLabel a label to show the actual players score
 *
 * @property readyButton a button to check whether the next player is ready to play
 */

class SwimGameScene(private val rootService: RootService): Refreshable,BoardGameScene(720, 480) {
    private val cardImageLoader = CardImageLoader()

    /**
     * button set up to call the knock function from player service
     * and nextPlayer from game service
     */
    private var knockButton = Button(
        posY = 350,
        posX =  530,
        width = 150,
        height = 25,
        text = "Knock"
    ).apply { onMouseClicked ={
        rootService.playerService.knock()
        rootService.gameService.nextPlayer()
    } }


    /**
     * button set up to call the pass function from player service
     * and nextPlayer from game service
     */
    private var passButton = Button(
        posY = 380,
        posX =  530,
        width = 150,
        height = 25,
        text = "Pass"
    ).apply { onMouseClicked ={
        rootService.playerService.pass()
        rootService.gameService.nextPlayer()
    } }

    /**
     * button set up to call the swapAll function from player service
     * and nextPlayer from game service
     */
    private var swapAllButton = Button(
        posY = 410,
        posX =  530,
        width = 150,
        height = 25,
        text = "Swap All"
    ).apply { onMouseClicked ={rootService.playerService.swapAll()
                rootService.gameService.nextPlayer()
    } }


    /**
     * button set up to call the select function from player service
     * with the indexes determined by the get indexOfSelected from
     * player service and nextPlayer from game service
     */
    private var swapOneButton = Button(
        posY = 440,
        posX =  530,
        width = 150,
        height = 25,
        text = "Swap One"
    ).apply {onMouseClicked = {
        val poolIndex = getIndexOfSelected(poolCard1,poolCard2,poolCard3)
        val handIndex = getIndexOfSelected(card1,card2,card3)
        rootService.playerService.swapOne(handIndex,poolIndex)
        rootService.gameService.nextPlayer()
    }
    }

    private var rightNameLabel = Label(
        posY = 80,
        posX = 600,
        width = 100,
        font = Font(color = Color.WHITE)
    )

    private var topNameLabel = Label(
        posY = 20,
        posX = 450,
        width = 100,
        font = Font(color = Color.WHITE)
    )

    private var leftNameLabel = Label(
        posY = 80,
        posX = 20,
        width = 100,
        font = Font(color = Color.WHITE)
    )

    private var mainNameLabel = Label(
        posY = 330,
        posX = 100,
        width = 100,
        font = Font(color = Color.WHITE)
    )

    private val linearLayoutTopPlayer = LinearLayout<CardView>(
        posX = 250,
        posY = -50,
        spacing = 0,
        width = 200,
        height = 200
    )


    private val linearLayoutLeftPlayer = LinearLayout<CardView>(
        posX = -60,
        posY = 80,
        spacing = 0,
        width = 200,
        height = 250
    ).apply { this.rotation = 90.0 }

    private val linearLayoutRightPlayer = LinearLayout<CardView>(
        posX = 530,
        posY = 80,
        spacing = 0,
        width = 200,
        height = 250
    ).apply { this.rotation = 90.0 }

    /**
     * the cards are down scaled and show by their back
     */
    private val card1 = CardView(
        posX = 200,
        posY = 300,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(0.55)
        this.showBack()
    }

    private val card2 = CardView(
        posX = 300,
        posY = 300,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(0.55)
        this.showBack()
    }

    private val card3 = CardView(
        posX = 400 ,
        posY = 300,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(0.55)
        this.showBack()
    }


    /**
     * the readyButton is set up enable the action buttons when clicked
     */
    private val readyButton = Button(
        posX = 285,
        posY = 175,
        width = 150,
        height = 75,
        text = "ready",
        visual = ColorVisual(119,198,110)
    ).apply {
        onMouseClicked ={
            knockButton.isDisabled = false
            passButton.isDisabled = false
            swapOneButton.isDisabled = false
            swapAllButton.isDisabled = false
        }
    }

    private val scoreLabel = Label(
        height = 40,
        width = 100,
        posY = 390,
        posX = 100,
        font = Font(size = 13, color = Color.ORANGE)
    )

    /**
     * the pool cards are also down scaled
     */
    private val poolCard1 = CardView(
        posX = card1.posX ,
        posY = card1.posY-190,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(card1.scale)}

    private val poolCard2 = CardView(
        posX = poolCard1.posX + 100,
        posY = poolCard1.posY,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(card1.scale)}

    private val poolCard3 = CardView(
        posX = poolCard2.posX + 100,
        posY = poolCard1.posY,
        front = ImageVisual(cardImageLoader.backImage)
    ).apply { this.scale(card1.scale)}


    private var mainActionLabel = Label(
        posY = mainNameLabel.posY +30,
        posX = mainNameLabel.posX,
        width = mainNameLabel.width,
        height = mainNameLabel.height,
        text = "test",
        font = Font(color = Color.YELLOW)
    )
    private var rightActionLabel = Label(
        posY = rightNameLabel.posY +30,
        posX = rightNameLabel.posX,
        width = rightNameLabel.width,
        height = rightNameLabel.height,
        text = "test",
        font = Font(color = Color.YELLOW)
    )
    private var leftActionLabel = Label(
        posY = leftNameLabel.posY +30,
        posX = leftNameLabel.posX,
        width = leftNameLabel.width,
        height = leftNameLabel.height,
        text = "test",
        font = Font(color = Color.YELLOW)
    )
    private var topActionLabel = Label(
        posY = topNameLabel.posY +30,
        posX = topNameLabel.posX,
        width = topNameLabel.width,
        height = topNameLabel.height,
        text = "test",
        font = Font(color = Color.YELLOW)
    )

    /**
     * a function to select the indexes based on their scale so the bigger cards are selected
     */
    private fun getIndexOfSelected(card1:CardView,card2:CardView,card3:CardView):Int{
        return if (card1.scale>card2.scale ) 0
        else if(card2.scale > card3.scale) 1
        else 2
    }

    /**
     * after knocking disappears the knockButton and the label gets changed
     * and the function refresh gets called
     */

    override fun refreshAfterKnock(){
        knockButton.isVisible = false
        mainActionLabel.text = rootService.currentGame!!.players[0].lastAction.toString()
        refresh()
    }

    /**
     * after swapping the label gets changed and refresh called
     */
    override fun refreshAfterSwap() {
        val currentGame = rootService.currentGame!!
        mainActionLabel.text = currentGame.players[0].lastAction.toString()
        refresh()

    }

    /**
     * after passing the label gets changed and refresh called
     */
    override fun refreshAfterPass() {
        mainActionLabel.text = rootService.currentGame!!.players[0].lastAction.toString()
        refresh()


    }

    /**
     * after calling next player the game will be ended if the is it possible if not
     * all the labels get refreshed base on the number of players
     */
    override fun refreshAfterAfterNextPlayer() {
        val currentGame = rootService.currentGame!!
        rootService.gameService.gameIsEnded()
        when(currentGame.players.size){
            3-> {
                mainNameLabel.text = currentGame.players[0].playerName
                rightNameLabel.text = currentGame.players[1].playerName
                topNameLabel.text = currentGame.players[2].playerName
                mainActionLabel.text = currentGame.players[0].lastAction.toString()
                rightActionLabel.text = currentGame.players[1].lastAction.toString()
                topActionLabel.text = currentGame.players[2].lastAction.toString()
            }
            4 -> {
                    mainNameLabel.text = currentGame.players[0].playerName
                    rightNameLabel.text = currentGame.players[1].playerName
                    topNameLabel.text = currentGame.players[2].playerName
                    leftNameLabel.text = currentGame.players[3].playerName
                    mainActionLabel.text = currentGame.players[0].lastAction.toString()
                    rightActionLabel.text = currentGame.players[1].lastAction.toString()
                    topActionLabel.text = currentGame.players[2].lastAction.toString()
                    leftActionLabel.text = currentGame.players[3].lastAction.toString()
            }
            else ->{
                mainNameLabel.text = currentGame.players[0].playerName
                topNameLabel.text = currentGame.players[1].playerName
                mainActionLabel.text = currentGame.players[0].lastAction.toString()
                topActionLabel.text = currentGame.players[1].lastAction.toString()
            }
        }

    }

    /**
     *
     *this function is meant to set up the game's visuals after starting a new game
     *
     *
     */
    override fun refreshAfterStartNewGame() {
        val currentGame = rootService.currentGame!!
        // the labels of the actual player are set
        mainActionLabel.text = currentGame.players[0].lastAction.toString()
        //refresh()
        mainNameLabel.text = currentGame.players[0].playerName
        /*
            based one the players name the labels will be set and the old components removed
            and the new ones added and the kick button is set to be visible
         */
        when(currentGame.players.size){
            //if there is 3 players there will be 3 players shown on the screen
            3-> {
                knockButton.isVisible = true

                rightNameLabel.text = currentGame.players[1].playerName
                topNameLabel.text = currentGame.players[2].playerName
                topActionLabel.text = currentGame.players[2].lastAction.toString()
                rightActionLabel.text = currentGame.players[1].lastAction.toString()


                removeComponents(rightNameLabel,topNameLabel,linearLayoutRightPlayer,
                    linearLayoutTopPlayer,linearLayoutLeftPlayer,leftNameLabel,
                    topActionLabel,rightActionLabel,leftActionLabel)


                addComponents(rightNameLabel,topNameLabel,
                linearLayoutRightPlayer,linearLayoutTopPlayer,
                    rightActionLabel,topActionLabel
                )
            }


            //if there is 4 players there will be 4 players shown on the screen
            4->{
                knockButton.isVisible = true

                rightNameLabel.text = currentGame.players[1].playerName
                topNameLabel.text = currentGame.players[2].playerName
                leftNameLabel.text = currentGame.players[3].playerName
                rightActionLabel.text = currentGame.players[1].lastAction.toString()
                topActionLabel.text = currentGame.players[2].lastAction.toString()
                leftActionLabel.text = currentGame.players[3].lastAction.toString()



                removeComponents(rightNameLabel,topNameLabel,linearLayoutRightPlayer,
                    linearLayoutTopPlayer,linearLayoutLeftPlayer,leftNameLabel,
                    topActionLabel,rightActionLabel,leftActionLabel)



                addComponents(rightNameLabel,topNameLabel,linearLayoutRightPlayer,
                linearLayoutTopPlayer,linearLayoutLeftPlayer,leftNameLabel,
                topActionLabel,rightActionLabel,leftActionLabel)
            }
            //if there is 2 players there will be 2 players shown on the screen
            else ->{
                topNameLabel.text = currentGame.players[1].playerName
                topActionLabel.text = currentGame.players[1].lastAction.toString()
                removeComponents(rightNameLabel,topNameLabel,linearLayoutRightPlayer,
                    linearLayoutTopPlayer,linearLayoutLeftPlayer,leftNameLabel,
                    topActionLabel,rightActionLabel,leftActionLabel)
                knockButton.isVisible = true
                addComponents(topNameLabel,topActionLabel,linearLayoutTopPlayer)
            }

        }
        knockButton.isDisabled = true
        passButton.isDisabled = true
        swapAllButton.isDisabled = true
        swapOneButton.isDisabled = true
        /*
            the readyButton is set up to call the refresh function and show the front side
            of the actual player
            and calculate the score of the player and show it on the screen after that the button
            disappears and the action buttons will be enabled only swap one button stays disabled
            and the actual player's score will be shown


         */

        readyButton.onMouseClicked = {
            refresh()


            card1.showFront()
            card2.showFront()
            card3.showFront()


            currentGame.players[0].calculateScore()
            scoreLabel.text  = "current score\n"+rootService.currentGame!!.players[0].score

            readyButton.isVisible = false

            knockButton.isDisabled = false
            passButton.isDisabled = false
            swapAllButton.isDisabled = false

            scoreLabel.isVisible = true
        }

    }

    /**
     * a messed up function that does a lot of things :(
     */
    private fun refresh(){
        val currentGame = rootService.currentGame!!
        currentGame.players[0].calculateScore()
        readyButton.isVisible=true
        scoreLabel.isVisible=false
        /*
        * the cards all get smaller again in case one of them got bigger after selecting it
        * */
        card1.scale = 0.55
        card2.scale= 0.55
        card3.scale = 0.55
        poolCard2.scale = 0.55
        poolCard1.scale = 0.55
        poolCard3.scale = 0.55
        /*
        * disable all buttons
        * */
        knockButton.isDisabled = true
        passButton.isDisabled = true
        swapOneButton.isDisabled = true
        swapAllButton.isDisabled = true

        /*
        * refresh the actual player's cards and the pool cards and show them from the back
        * */

        card1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.players[0].cards[0].suit,
            currentGame.players[0].cards[0].value))
        card1.showBack()

        card2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.players[0].cards[1].suit,
            currentGame.players[0].cards[1].value))
        card2.showBack()

        card3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.players[0].cards[2].suit,
            currentGame.players[0].cards[2].value))
        card3.showBack()

        poolCard1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.poolCards[0].suit,
            currentGame.poolCards[0].value))

        poolCard1.showFront()
        poolCard2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.poolCards[1].suit,
            currentGame.poolCards[1].value))

        poolCard2.showFront()
        poolCard3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(currentGame.poolCards[2].suit,
            currentGame.poolCards[2].value))

        poolCard3.showFront()
    }



    init {
        // add some fake cards to the linearLayouts
        linearLayoutRightPlayer.addAll(
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4) },
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)},
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)}
        )

        linearLayoutTopPlayer.addAll(
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4) },
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)},
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)}
        )

        linearLayoutLeftPlayer.addAll(
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4) },
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)},
            CardView(front = ImageVisual(cardImageLoader.backImage)).apply { scale(0.4)}
        )

        // this is a hole logic to do the animation of a card gets bigger when it is clicked
        // and only one card from the pool and one card from the player's hand can be selected
        //at a time

        //begin
        card1.apply { this.scale(0.55)
            this.showBack()
            onMouseClicked= {
                if(scale == 0.55) {
                    scale = 0.7
                    card3.scale = 0.55
                    card2.scale = 0.55
                }
                else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))
                }
            }
        }
        card2.apply {
            this.scale(0.55)
            this.showBack()
            onMouseClicked = {

                if (scale == 0.55) {
                    scale = 0.7
                    card3.scale = 0.55
                    card1.scale = 0.55
                } else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))
                }
            }
        }
        card3.apply {
            this.scale(0.55)
            this.showBack()
            onMouseClicked = {
                if (scale == 0.55) {
                    scale = 0.7
                    card1.scale = 0.55
                    card2.scale = 0.55
                } else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))
                }
            }
        }

        poolCard1.apply { this.scale(0.55)
            this.showBack()
            onMouseClicked= {
                if(scale == 0.55) {
                    scale = 0.7
                    poolCard3.scale = 0.55
                    poolCard2.scale = 0.55
                }
                else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))
                }
            }
        }
        poolCard2.apply {
            this.scale(0.55)
            this.showBack()
            onMouseClicked = {
                if (scale == 0.55) {
                    scale = 0.7
                    poolCard3.scale = 0.55
                    poolCard1.scale = 0.55
                } else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))
                }
            }
        }
        poolCard3.apply {
            this.scale(0.55)
            this.showBack()
            onMouseClicked = {
                if (scale == 0.55) {
                    scale = 0.7
                    poolCard1.scale = 0.55
                    poolCard2.scale = 0.55
                } else scale = 0.55
                swapOneButton.apply {
                    isDisabled = !(((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                            ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7)))

                }
            }
        }
        //end



        // the swap one button gets enabled only when two cards are selected
        swapOneButton.apply {
            if (((card1.scale==0.7)||(card2.scale==0.7)||(card3.scale==0.7))&&
                ((poolCard1.scale == 0.7)||(poolCard2.scale == 0.7)||(poolCard3.scale==0.7))){
                isDisabled = false
            }
        }
        addComponents(
            poolCard1,
            poolCard2,
            poolCard3,
            card1,
            card2,
            card3,
            scoreLabel,
            mainNameLabel,
            readyButton,
            knockButton,
            swapAllButton,
            swapOneButton,
            passButton,
            mainActionLabel)
            background = ImageVisual(path = "background.jpg")
    }
}