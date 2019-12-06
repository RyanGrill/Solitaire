package com.example.solitaire


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet



class GameActivity : AppCompatActivity(){

    var moves = 0

    lateinit var tableauLayout: Array<ConstraintLayout>
    lateinit var tableauDisplays: Array<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        tableauDisplays = Array<ImageButton>(7, fun(i : Int ): ImageButton{
            val btn = when(i){
                0 -> findViewById<ImageButton>(R.id.spot_tb1)
                1 -> findViewById<ImageButton>(R.id.spot_tb2)
                2 -> findViewById<ImageButton>(R.id.spot_tb3)
                3 -> findViewById<ImageButton>(R.id.spot_tb4)
                4 -> findViewById<ImageButton>(R.id.spot_tb5)
                5 -> findViewById<ImageButton>(R.id.spot_tb6)
                else -> findViewById<ImageButton>(R.id.spot_tb7)
            }
            return btn
        })

        tableauLayout = Array<ConstraintLayout>(7, fun(i : Int ): ConstraintLayout{
            val layout = when(i){
                0 -> findViewById<ConstraintLayout>(R.id.tableau1)
                1 -> findViewById<ConstraintLayout>(R.id.tableau2)
                2 -> findViewById<ConstraintLayout>(R.id.tableau3)
                3 -> findViewById<ConstraintLayout>(R.id.tableau4)
                4 -> findViewById<ConstraintLayout>(R.id.tableau5)
                5 -> findViewById<ConstraintLayout>(R.id.tableau6)
                else -> findViewById<ConstraintLayout>(R.id.tableau7)
            }
            return layout
        })
        GameModel.restart()
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        updateTableaus()
    }

    fun getCardId(card: Card):Int
    {
        val imgName = "${card.suit[0]}${card.value}"
        return resources.getIdentifier(imgName, "drawable", packageName)
    }

    fun deckTapped(view: View){
        GameModel.onDeckTap()
        val cardId = getCardId(GameModel.wastePile.last())
        val wastePileID = findViewById<ImageButton>(R.id.spot_waste)
        wastePileID.setImageResource(cardId)
        moves++
    }

    fun wasteTapped(view: View){
        GameModel.onWasteTap()
        moves++
    }

    fun menuButtonTapped(view: View){
        val intent = Intent(this, Menu::class.java)
        val menuString = "fromGame"
        intent.putExtra("menuCheck", menuString)
        startActivityForResult(intent, 93)
    }

    private fun updateTableaus(){
        var tabNum = 1
        GameModel.tableauPiles.forEach {
            val cards = it.tableauCards
            if(cards.size == 1)
            {
                var id = getCardId(it.tableauCards.first())
                tableauDisplays[0].setImageResource(id)
            }
            else{
                var prev: ImageButton = tableauDisplays[0]
                cards.forEach{
                    if(cards.first() == it){
                        var id = getCardId(cards.first())
                        prev.setImageResource(id)
                    }
                    else{
                        val constraints: ConstraintSet = ConstraintSet()
                        constraints.clone(tableauLayout[tabNum])
                        val img = ImageButton(applicationContext)
                        var id = getCardId(it)
                        img.setImageResource(id)
                        constraints.addToVerticalChain(img.id, prev.id, 0)
                        img.layoutParams.height = 65
                        img.layoutParams.width = 45
                        constraints.applyTo(tableauLayout[tabNum])
                    }//https://resizeimage.net/
                }
            }
        }

    }
}
