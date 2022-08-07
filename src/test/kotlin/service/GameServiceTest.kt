package service
import java.lang.*
import kotlin.test.*

class GameServiceTest {
    /**
     * this test creates a new game and checks whether:
     * - the given playerCount equals the size of list of players
     * - all players have 3 cards
     * - there is 3 cards in the pool
     * */
    @Test
    fun startGameTest(){
        val rootService = RootService()

        rootService.gameService.startNewGame(3)
        assertEquals(rootService.currentGame!!.players.size, 3)
        assertEquals(rootService.currentGame!!.players[0].cards.size, 3)
        assertEquals(rootService.currentGame!!.players[1].cards.size, 3)
        assertEquals(rootService.currentGame!!.players[2].cards.size, 3)
        assertEquals(rootService.currentGame!!.poolCards.size,3)
    }

    /**
     * this test gives a list with an empty string to the method configNames
     * and verifies that the player that receives an empty String still have the name
     * Player[i]
     * */
    @Test
    fun configNameTestWithEmptyElement(){
        val names = listOf("Anna", "Bob", "")
        val rootService = RootService()
        val gameService = GameService(rootService)
        gameService.startNewGame(3)
        gameService.configName(names)
        assertEquals(rootService.currentGame!!.players[0].playerName,names[0])
        assertEquals(rootService.currentGame!!.players[1].playerName,names[1])
        assertEquals(rootService.currentGame!!.players[2].playerName,"Player3")
    }
    /**
     * this test checks whether the list of names has the same size as the list of players
     * */
    @Test

    fun configNameTestWithUnequalSize(){
        val names = listOf("Anna", "Bob")
        val rootService = RootService()
        val gameService = GameService(rootService)
        gameService.startNewGame(3)
        assertFailsWith<IllegalArgumentException> { gameService.configName(names) }
    }

    /**
     * this test verifies that drawn cards are deleted from the draw stack
     */
    @Test
    fun swapPoolTest(){
        val rootService = RootService()
        val gameService = GameService(rootService)
        gameService.startNewGame(3)
        val drawStack = rootService.currentGame!!.drawStack
        val upperCards = listOf(drawStack[0],drawStack[1],drawStack[2])
        gameService.swapPool()
        assertEquals(upperCards,rootService.currentGame!!.poolCards)
    }

    /**
     * this test verifies that the last player is moved to the end of the list
     * and the second player takes his place after calling the nextPlayer function
     */
    @Test
    fun nextPlayerTest(){
        val rootService = RootService()
        val gameService = GameService(rootService)
        gameService.startNewGame(3)
        val firstPlayer = rootService.currentGame!!.players[0]
        val secondPlayer = rootService.currentGame!!.players[1]
        gameService.nextPlayer()
        assertEquals(firstPlayer, rootService.currentGame!!.players[rootService.currentGame!!.players.lastIndex])
        assertEquals(secondPlayer,rootService.currentGame!!.players[0])
    }

    @Test

    fun startGameTwiceTest(){
        val rootService = RootService()
        val gameService = GameService(rootService)
        gameService.startNewGame(3)
        gameService.startNewGame(2)
        assertEquals(rootService.currentGame!!.players.size,2)
    }
}