package view

/**
 * interface for refreshing scenes after a change in the service layer
 */

interface Refreshable {
    fun refreshAfterSwap(){}
    fun refreshAfterPass(){}
    fun refreshAfterKnock(){}
    fun refreshAfterStartNewGame(){}
    fun refreshAfterAfterNextPlayer(){}
    fun refreshAfterEndGame(){}
    fun refreshAfterRestart(){}
}