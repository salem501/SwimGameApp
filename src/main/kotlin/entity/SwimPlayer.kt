package entity

/**
 * diese Klasse ist eine Entität, die einen Spieler repräsentiert und hat 4
 * Attribute.
 *
 * @param score die gesamte Punkte eines Spielers
 * @param cards die Karten in der Hand eines Spielers, die Liste muss der
 *              Größe 3 sein
 * @param lastAction eine Variable, die die letzte vom Spieler ausgeführte
*               Aktion beinhaltet
 */
data class SwimPlayer (var playerName: String){
    var cards = mutableListOf<SwimCard>()
    var lastAction = Action.NO_ACTION
    var score =  0F
    init{
        if(playerName == ""){
            throw IllegalArgumentException("Player name is not valid")
        }
    }


    fun calculateScore(){
        if(!((cards[0].suit == cards[1].suit) &&
                    (cards[1].suit == cards[2].suit) &&
                    (cards[0].suit == cards[2].suit)) &&
                    (cards[0].value == cards[1].value) &&
                    (cards[1].value == cards[2].value) ){
                    score = 30.5F
                    return
        }
        var clubs = 0F
        var spades = 0F
        var hearts = 0F
        var diamonds = 0F
        var value : Float
        for(card in cards){
            value = card.valueOf()
            when(card.suit){
                CardSuit.CLUBS -> clubs += value
                CardSuit.SPADES -> spades += value
                CardSuit.DIAMONDS -> diamonds += value
                CardSuit.HEARTS -> hearts += value
            }
        }
        score = maxOf(clubs, spades, diamonds, hearts)
    }
}