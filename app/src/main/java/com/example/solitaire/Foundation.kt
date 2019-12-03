package com.example.solitaire

class Foundation(val suit: String)
{
    var inPileCards : MutableList<Card> = mutableListOf()

    //on calling for restart, clears piles
    fun reset() {
        inPileCards.clear()
    }

    /*on attempt to add card, compares with cards in pile(if any), then
    adds if conditions are met and gives a true response, otherwise gives
    false
     */
    fun addCard(card: Card): Boolean{
        var valueToMatch = 0
        //check pile's value
        if(inPileCards.size > 0)
            valueToMatch = inPileCards.last().value + 1

        //check card's value & suit
        if(card.suit == suit && card.value == valueToMatch) {
            inPileCards.add(card)
            return true
        }

        return false
    }


}
