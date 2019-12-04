package com.example.solitaire

class Deck {
    val cards : Array<Card> = Array(52, fun(i : Int): Card {
        val value = i % 13
        val suit = when (i / 13) {
            0 -> "diamonds"
            1 -> "hearts"
            2 -> "clubs"
            else -> "spades"
        }

        return Card(value, suit)

    })
    var inDeckCards = cards.toMutableList()

    fun drawCard(): Card
    {
        return inDeckCards.removeAt(0)
    }

    fun restart()
    {
        inDeckCards = cards.toMutableList()
        for(i in inDeckCards)
            i.faceUp = false
        inDeckCards.shuffle()
    }
}
