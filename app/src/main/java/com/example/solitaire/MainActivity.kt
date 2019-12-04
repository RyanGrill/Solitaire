package com.example.solitaire


import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getCardId(card: Card):Int
    {
        val imgName = "${card.suit[0]}${card.value}"
        return resources.getIdentifier(imgName, "drawable", packageName)
    }



}
