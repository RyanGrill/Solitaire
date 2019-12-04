package com.example.solitaire

class GamePresenter {
    var view: GameView? = null

    fun setGameView(gameView: GameView){
        view = gameView
    }

    fun deckTapped(){
        GameModel.onDeckTap()
        view?.update()
    }

    fun wasteTapped(){
        GameModel.onDeckTap()
        view?.update()
    }

    fun tableauTapped(tableauIndex: Int, cardIndex: Int){
        GameModel.onTableauTap(tableauIndex, cardIndex)
        view?.update()
    }
}