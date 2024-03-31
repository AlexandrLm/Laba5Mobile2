package com.example.laba5mobile2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var player1Name : EditText
    lateinit var player2Name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1Name = findViewById(R.id.player1Text)
        player2Name = findViewById(R.id.player2Text)
    }
    fun enterButtonPress(v : View) {
        if (player1Name.text.toString() != "" && player2Name.text.toString() != "" && player2Name.text.toString() != player1Name.text.toString()) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("player1Name", player1Name.text.toString())
            intent.putExtra("player2Name", player2Name.text.toString())
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "Введите все имена", Toast.LENGTH_SHORT).show()
        }
    }


}