package service

import entity.*

/**
 * Service layer class that provides the logic for actions not directly
 * related to a single player.
 * @param rootService
 * @property players a list of the existing players
 */
class GameService (private val rootService:RootService): AbstractRefreshingService(){


    /**
     * this functions starts a new game. It initializes the list
     * of [players], then creates  a [SwimGame] using this list
     * and stores it in [RootService.currentGame] after
     * that it generates a shuffled stack and deals the cards
     *
     * */
    fun startNewGame(playerCount: Int){

        rootService.currentGame = SwimGame(playerCount)

        initializeGame(playerCount)



        rootService.currentGame!!.drawStack = generateStack() as MutableList<SwimCard>

        dealCards()

        onAllRefreshables { refreshAfterStartNewGame() }
    }

    private fun isGameEnded() : Boolean{

        val game = rootService.currentGame
        checkNotNull(game){"No game currently running"}
        return true
    }

    /**
     * this function is for cards dealing, it iterates all the players
     * and adds 3 cards from the stack to their hands
     * After that it adds 3 cards to the poolCards list
     */
    private fun dealCards(){

        val currentGame = rootService.currentGame

        for (player in rootService.currentGame!!.players){
            repeat(3){
                player.cards.add(drawCard())
            }
        }

        repeat(3){
            currentGame!!.poolCards.add(drawCard())
        }
    }

    /**
     * this function adds the first player at the end of the list
     * and removes the first element so this player wouldn't be
     * redundant
     */
    fun nextPlayer(){

        checkNotNull(rootService.currentGame) { "Current game not initialized" }

        val currentGame = rootService.currentGame!!

        currentGame.players.add(currentGame.players.first())
        currentGame.players.removeFirst()

        if (currentGame.players.filter { player -> player.lastAction == Action.PASS }.size
            == currentGame.players.size
            && currentGame.drawStack.size>=3
                ) {
            rootService.gameService.swapPool()
            onAllRefreshables { refreshAfterSwap()}
            currentGame.players.forEach { player -> player.lastAction = Action.NO_ACTION }
        }
        onAllRefreshables { refreshAfterAfterNextPlayer()}
    }

    fun gameIsEnded(){
        checkNotNull(rootService.currentGame) { "Current game not initialized" }

        val currentGame = rootService.currentGame!!

        if (currentGame.players.filter { player -> player.lastAction == Action.PASS }.size
            == currentGame.players.size && currentGame.drawStack.size<3
        ) onAllRefreshables { refreshAfterEndGame()}

        if (currentGame.players[0].lastAction == Action.KNOCK) {
            onAllRefreshables { refreshAfterEndGame()}
        }


    }

    /**
     * this function removes all cards from the pool and then
     * adds new 3 cards
     */
    fun swapPool(){

        rootService.currentGame!!.poolCards.clear()

        for(i in 1 .. 3){
            rootService.currentGame!!.poolCards.add(drawCard())
        }

    }

    /**
     * this function draws a card from stack and removes it from
     * the stack
     * @return drawnCard the drawn card
     */
    private fun drawCard():SwimCard{
        val drawStack = rootService.currentGame!!.drawStack
        val drawnCard = drawStack[0]

        drawStack.removeFirst()

        return drawnCard
    }
    /**
     * this function creates new players in the players list
     * @param playerCount number of players to be created
     */
    private fun initializeGame(playerCount: Int){

        for (i in 1..playerCount) {
            rootService.currentGame!!.players.add(SwimPlayer("Player${i}"))
        }

    }

    /**
     * this function is for changing the names of players
     * it checks whether the names list has the same size as players
     * and then iterates the players and checks if the given name
     * is not empty than it will be changed else the name stays
     * the same
     */

    fun configName(names: List<String>){
        val players = rootService.currentGame!!.players

        require(names.size == players.size)

        for (i in players.indices){

            if(names[i] != ""){
                players[i].playerName = names[i]
            }

        }
    }

    /**
     * this function generates a list of cards with all possible
     * combinations
     */
    private fun generateStack() = List(32) { index ->
        SwimCard(
            CardSuit.values()[index / 8],
            CardValue.values()[(index % 8) + 5]
        )
    }.shuffled()
}