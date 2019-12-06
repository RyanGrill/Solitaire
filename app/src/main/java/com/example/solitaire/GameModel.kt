package com.example.solitaire

import android.view.View

object GameModel
{
    val wastePile : MutableList<Card> = mutableListOf()

    val deck = Deck()
    val foundationPiles = arrayOf(Foundation("diamonds"), Foundation("clubs"), Foundation("hearts"), Foundation("spades"))
    val tableauPiles = Array(7, { Tableau() } )

    //restarts all piles
    fun restart(){
        wastePile.clear()
        foundationPiles.forEach( { it.reset() })
        deck.restart()
        //for each pile...
        tableauPiles.forEachIndexed { i, _ ->
            //pull cards from the deck
            val cardInPile: MutableList<Card> = Array(i+1, {deck.drawCard()}).toMutableList()
            //and turn them into new piles
            tableauPiles[i] = Tableau(cardInPile)
        }

    }

    //on touching a deck, cards are moved to the waste pile
    fun onDeckTap() {
        if (deck.inDeckCards.size > 0) {
            val card = deck.drawCard()
            card.faceUp = true
            wastePile.add(card)
        } else {
            deck.inDeckCards = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    //on touching the waste pile, cards are played, if they can be
    fun onWasteTap() {
        if(wastePile.size > 0) {
            val card = wastePile.last()
            if(playCard(card))
                wastePile.remove(card)
        }
    }

    //checks and plays the card that is passed to it, checking foundations before the tableau piles
    private fun playCard(card: Card):Boolean {
        foundationPiles.forEach {
            if(it.addCard(card)) {
                return true

            }
        }

        tableauPiles.forEach {
            if(it.addCards(mutableListOf(card))){
                return true
            }
        }

        return false
    }

    //on touching a tableau, cards are played depending on if they can be
    fun onTableauTap(tableauIndex: Int, cardIndex: Int){
        val selectedTableau = tableauPiles[tableauIndex]
        if(selectedTableau.tableauCards.size > 0) {
            if (selectedTableau.tableauCards[cardIndex].faceUp) {
                val selectedCards = selectedTableau.tableauCards.subList(
                    cardIndex,
                    selectedTableau.tableauCards.lastIndex + 1
                )
                if (playCards(selectedCards))
                    selectedTableau.removeCards(cardIndex)
            }
        }
    }

    //checks the cards and plays them if it can
    private fun playCards(cards: MutableList<Card>): Boolean{
        if(cards.size == 1)
            return playCard(cards.first())
        else{
            tableauPiles.forEach {
                if(it.addCards(cards))
                    return true
            }
        }
        return false
    }
}