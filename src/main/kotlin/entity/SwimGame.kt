package entity

/**
 * diese Klasse ist eine Entität, die das ganze Spiel repräsentiert.
 * @param playerCount Anzahl der Spieler
 */
data class SwimGame (val playerCount:Int){
    val players =  mutableListOf<SwimPlayer>()
    var poolCards = mutableListOf<SwimCard>()
    var drawStack = mutableListOf<SwimCard>()
    init {
        require((playerCount>=2)&&(playerCount<=4))
    }
}