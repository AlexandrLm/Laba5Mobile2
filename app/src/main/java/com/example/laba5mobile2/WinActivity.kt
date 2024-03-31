package com.example.laba5mobile2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class WinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        findViewById<TextView>(R.id.whoWinText).text = "Победил ${intent.extras?.getString("name")}"
    }
    fun againButtonPress(v : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}