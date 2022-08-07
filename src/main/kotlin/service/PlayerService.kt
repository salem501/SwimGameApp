package service

import entity.Action

/**
 * Service layer class that provides the logic for the two possible actions a player
 * can take in War: drawing from the left stack or drawing from right stack.

 */
class PlayerService (private val rootService : RootService): AbstractRefreshingService(){

    /**
     * function for knock action. It sets the last action to [Action.KNOCK]
     * and calls the nextPlayer function and refreshes the game
     *
     */
    fun knock(){
        rootService.currentGame!!.players[0].lastAction = Action.KNOCK
        onAllRefreshables { refreshAfterKnock()}
    }

    /**
     * function that swaps the cards in the player's hand and the cards
     * in the pool and then calls the nextPlayer function and refreshes
     */

    fun swapAll(){

        val currentGame = rootService.currentGame!!

        currentGame.players[0].lastAction = Action.SWAP_ALL

        //swap line
        currentGame.players[0].cards = currentGame.poolCards.also {
                currentGame.poolCards = currentGame.players[0].cards
            }

        onAllRefreshables { refreshAfterSwap()}
    }
    /**
     * function that swap one card from the player's hand and one
     * from the pool
     * @param playerCardIndex the index of the card that the player wants to swap
     * @param poolCardIndex the index of the card that the player wants to have from the pool
     * */

    fun swapOne(playerCardIndex:Int, poolCardIndex:Int){

        require((playerCardIndex in 0 .. 2) && (poolCardIndex in 0 .. 2)){
            "The given indexes are not valid"
        }

        val currentGame = rootService.currentGame!!

        currentGame.players[0].lastAction = Action.SWAP_ONE

        //swap line
        currentGame.players[0].cards[playerCardIndex] = currentGame.poolCards[poolCardIndex].also {

            currentGame.poolCards[poolCardIndex] = currentGame.players[0].cards[playerCardIndex]

            }

        onAllRefreshables { refreshAfterSwap()}
    }
    /**
     * function for pass action
     *
     * */
    fun pass(){
        rootService.currentGame!!.players[0].lastAction = Action.PASS
        onAllRefreshables { refreshAfterPass()}
    }
}