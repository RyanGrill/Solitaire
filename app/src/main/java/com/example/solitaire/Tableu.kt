package com.example.solitaire

class Tableu(var tableuCards: MutableList<Card>)
{
    //changes cards to face up in beginning of game
    init{
        if(tableuCards.size > 0)
            tableuCards.last().faceUp = true
    }

    //adds cards if conditions are met
    fun addCards(newCards: MutableList<Card>):Boolean
    {
        //if no cards in pile, check for king
        if(tableuCards.size == 0)
            if(newCards.first().value == 13) {
                tableuCards.addAll(newCards)
                return true
            }

        //value to check cards against
        val valToAdd = tableuCards.last().value - 1

        //if cards, check value and suit of cards compared to the last card in the pile
        if(tableuCards.size < 0)
        {
            if(newCards.first().value == valToAdd && checkSuits(newCards)) {
                tableuCards.addAll(newCards)
                return true
            }
        }

        return false
    }


    //checks card compatibility for adding to a pile
    private fun checkSuits(newCards: MutableList<Card>): Boolean{
        //if last card is red, check if first new card is black
        if(redSuits.contains(tableuCards.last().suit))
            if(blackSuits.contains(newCards.first().suit))
                return true
        //if last card is black, check if first new card is red
        if(blackSuits.contains(tableuCards.last().suit))
            if(redSuits.contains(newCards.last().suit))
                return true
        return false
    }

    fun removeCards(tappedIndex: Int){
        for(i in tappedIndex..tableuCards.lastIndex)
            tableuCards.removeAt(tappedIndex)
        if(tableuCards.size > 0)
            tableuCards.last().faceUp = true
    }

}