package entity

data class SwimCard (val suit: CardSuit, val value: CardValue) {
    override fun toString() = "$suit$value"

    fun valueOf():Float{
        return when(this.value){
            CardValue.SEVEN -> 7F
            CardValue.EIGHT -> 8F
            CardValue.NINE -> 9F
            CardValue.TEN -> 10F
            CardValue.JACK -> 10F
            CardValue.QUEEN -> 10F
            CardValue.KING -> 10F
            CardValue.ACE -> 11F
            else -> throw IllegalStateException("Error getting the value of the card")
        }
    }
}