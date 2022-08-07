package service

import entity.SwimGame
import view.Refreshable

class RootService {

    val gameService = GameService(this)
    val playerService = PlayerService(this)

    /**
     * The currently active game. Can be `null`, if no game has started yet.
     */
    var currentGame : SwimGame? = null

    fun addRefreshable(newRefreshable: Refreshable) {
        gameService.addRefreshable(newRefreshable)
        playerService.addRefreshable(newRefreshable)
    }
    fun addRefreshables(vararg newRefreshables: Refreshable) {
        newRefreshables.forEach { addRefreshable(it) }
    }
}