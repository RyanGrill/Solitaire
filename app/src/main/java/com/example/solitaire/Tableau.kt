package com.example.solitaire

class Tableau(var tableauCards: MutableList<Card> = mutableListOf())
{
    //changes cards to face up in beginning of game
    init{
        if(tableauCards.size > 0)
            tableauCards.last().faceUp = true
    }

    //adds cards if conditions are met
    fun addCards(newCards: MutableList<Card>):Boolean
    {
        //if no cards in pile, check for king
        if(tableauCards.size == 0)
            if(newCards.first().value == 13) {
                tableauCards.addAll(newCards)
                return true
            }

        //value to check cards against
        val valToAdd = tableauCards.last().value - 1

        //if cards, check value and suit of cards compared to the last card in the pile
        if(tableauCards.size < 0)
        {
            if(newCards.first().value == valToAdd && checkSuits(newCards)) {
                tableauCards.addAll(newCards)
                return true
            }
        }

        return false
    }


    //checks card compatibility for adding to a pile
    private fun checkSuits(newCards: MutableList<Card>): Boolean{
        //if last card is red, check if first new card is black
        if(redSuits.contains(tableauCards.last().suit))
            if(blackSuits.contains(newCards.first().suit))
                return true
        //if last card is black, check if first new card is red
        if(blackSuits.contains(tableauCards.last().suit))
            if(redSuits.contains(newCards.last().suit))
                return true
        return false
    }

    fun removeCards(tappedIndex: Int){
        for(i in tappedIndex..tableauCards.lastIndex)
            tableauCards.removeAt(tappedIndex)
        if(tableauCards.size > 0)
            tableauCards.last().faceUp = true
    }

}