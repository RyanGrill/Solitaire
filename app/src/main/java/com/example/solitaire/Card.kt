package com.example.solitaire

val redSuits = arrayOf("diamonds", "hearts")
val blackSuits = arrayOf("clubs", "spades")
val cardMap = mapOf(0 to "a", 1 to "2", 2 to "3" , 3 to "4", 4 to "5", 5 to "6", 6 to "7", 7 to "8", 8 to "9", 9 to "10", 10 to "j", 11 to "q", 12 to "k")

class Card(value: Int, suit: String){
    val value = value
    val suit = suit
    var faceUp = false
}
