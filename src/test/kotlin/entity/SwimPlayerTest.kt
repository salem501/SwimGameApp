package entity
import kotlin.test.*

class SwimPlayerTest {

    @Test
    fun calculateScoreCaseInitScore() {
        val swimPlayer = SwimPlayer("Player1")
        assertEquals(swimPlayer.score, 0f)
    }
    @Test
    fun calculateScore1(){
        val swimPlayer = SwimPlayer("Player1")
        swimPlayer.cards = mutableListOf(SwimCard(CardSuit.DIAMONDS,CardValue.NINE),
            SwimCard(CardSuit.DIAMONDS,CardValue.JACK),
            SwimCard(CardSuit.DIAMONDS,CardValue.ACE))

        swimPlayer.calculateScore()
        assertEquals(swimPlayer.score,30f)
    }
    @Test
    fun calculateScore2(){
        val swimPlayer = SwimPlayer("Player1")
        swimPlayer.cards = mutableListOf(SwimCard(CardSuit.HEARTS,CardValue.SEVEN),
            SwimCard(CardSuit.HEARTS,CardValue.TEN),
            SwimCard(CardSuit.HEARTS,CardValue.JACK))
        swimPlayer.calculateScore()
        assertEquals(swimPlayer.score,27f)
    }

    @Test
    fun calculateScore3(){
        val swimPlayer = SwimPlayer("Player1")
        swimPlayer.cards = mutableListOf(SwimCard(CardSuit.SPADES,CardValue.EIGHT),
            SwimCard(CardSuit.DIAMONDS,CardValue.EIGHT),
            SwimCard(CardSuit.HEARTS,CardValue.EIGHT))
        swimPlayer.calculateScore()
        assertEquals(swimPlayer.score,30.5F)
    }
}