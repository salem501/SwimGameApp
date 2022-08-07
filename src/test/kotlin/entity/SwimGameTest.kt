package entity
import kotlin.test.*

class SwimGameTest {
    private val drawStack = listOf(SwimCard(CardSuit.DIAMONDS,CardValue.KING),
                                    SwimCard(CardSuit.CLUBS,CardValue.EIGHT),
                                    SwimCard(CardSuit.SPADES,CardValue.ACE),
                                    SwimCard(CardSuit.HEARTS,CardValue.TWO),
                                    SwimCard(CardSuit.HEARTS,CardValue.ACE),
                                    SwimCard(CardSuit.DIAMONDS,CardValue.NINE))

    private val poolCards = listOf(SwimCard(CardSuit.DIAMONDS,CardValue.KING),
                                    SwimCard(CardSuit.CLUBS,CardValue.EIGHT),
                                    SwimCard(CardSuit.SPADES,CardValue.ACE))
    private val playersList = listOf(SwimPlayer("Bob"),
        SwimPlayer("Anna")) as MutableList
    private var swimGame : SwimGame? = null

    /**
     * testet die Erstellung eines Spieles mit gültigen Daten
     */

    /*@Test
    fun caseOne(){
        swimGame = SwimGame(playersList)
        assertEquals(poolCards.size,3)
        assertContains(2..4, playersList.size)

    }*/
}