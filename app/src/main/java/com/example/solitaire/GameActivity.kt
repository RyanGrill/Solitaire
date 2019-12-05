package com.example.solitaire


import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val bottomLayout = findViewById<RelativeLayout>(R.id.bottomLayout)
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    fun getCardId(card: Card):Int
    {
        val imgName = "${card.suit[0]}${card.value}"
        return resources.getIdentifier(imgName, "drawable", packageName)
    }

    //https://android--code.blogspot.com/2015/08/android-imageview-programmatically.html


}
