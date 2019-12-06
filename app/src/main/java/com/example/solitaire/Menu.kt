package com.example.solitaire

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val intent = intent

        if(intent.getStringExtra("menuCheck") == null)
        {
            val resumeButton = findViewById<Button>(R.id.resumeButton)
            resumeButton.visibility = View.INVISIBLE
        }




        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    fun startGame(view: View){
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun goBack(view: View){
        val intent = Intent()
        setResult(93, intent)
        finish()
    }




}