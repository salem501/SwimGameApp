package service

import entity.Action
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PlayerServiceTest {



    /**
     * checks the last action
     * the passed variable will be removed
     * */
    @Test
    fun passTest(){
        val rootService = RootService()
        rootService.gameService.startNewGame(3)
        val playerService = rootService.playerService

        playerService.pass()

        assertEquals(rootService.currentGame!!.players[0].lastAction, Action.PASS)
    }

    /**
     * checks the last action
     * the playerKnocked will be removed
     */
    @Test

    fun knockTest(){
        val rootService = RootService()
        val gameService = rootService.gameService
        val playerService = rootService.playerService

        gameService.startNewGame(3)

        playerService.knock()

        assertEquals(rootService.currentGame!!.players[0].lastAction, Action.KNOCK)
    }

    /**
     * checks
     * -last action
     * -the hand cards of the last Player equals to the old pool
     * - the actual pool equals the old cards of the last player
     */

    @Test

    fun swapAllTest(){
        val rootService = RootService()
        val gameService = rootService.gameService
        val playerService = rootService.playerService

        gameService.startNewGame(3)

        val currentGame = rootService.currentGame!!
        val poolCards = currentGame.poolCards
        val playerCards = currentGame.players[0].cards

        playerService.swapAll()

        assertEquals(poolCards, currentGame.players[0].cards)
        assertEquals(playerCards, currentGame.poolCards)
        assertEquals(currentGame.players[0].lastAction,Action.SWAP_ALL)

    }

    @Test

    fun swapOneWithValidIndexTest(){
        val rootService = RootService()
        val gameService = rootService.gameService
        val playerService = rootService.playerService

        gameService.startNewGame(3)

        val playerCard = rootService.currentGame!!.players[0].cards[1]
        val poolCard = rootService.currentGame!!.poolCards[2]

        playerService.swapOne(1,2)

        assertEquals(rootService.currentGame!!.players[0].cards[1],poolCard)
        assertEquals(rootService.currentGame!!.poolCards[2],playerCard)
        assertEquals(rootService.currentGame!!.players[0].lastAction, Action.SWAP_ONE)
    }
    @Test
    fun swapOneWithInvalidIndexTest(){
        val rootService = RootService()
        val gameService = rootService.gameService
        val playerService = rootService.playerService

        gameService.startNewGame(3)

        assertFailsWith<java.lang.IllegalArgumentException> { playerService.swapOne(0,4) }
    }
}