package com.example.laba5mobile2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SelectActivity : AppCompatActivity() {
    var attack : String = "none"
    var defend : String = "none"
    var power : Int = 0 // 0 - обычная атака; 1 - сильная атака
    var energy : Int = 0

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        findViewById<TextView>(R.id.textView2).text = intent.extras?.getString("name")
        energy = intent.getIntExtra("energy", 0)
        if (energy < 1){
            findViewById<Button>(R.id.headButton).isClickable = false
            findViewById<Button>(R.id.bodyButton).isClickable = false
            findViewById<Button>(R.id.legButton).isClickable = false
            findViewById<Button>(R.id.headButton).isActivated = true
            findViewById<Button>(R.id.bodyButton).isActivated = true
            findViewById<Button>(R.id.legButton).isActivated = true
        }
        if (energy < 2){
            findViewById<Button>(R.id.heavyHeadButton).isClickable = false
            findViewById<Button>(R.id.heavyBodyButton).isClickable = false
            findViewById<Button>(R.id.heavyLegButton).isClickable = false
            findViewById<Button>(R.id.heavyHeadButton).isActivated = true
            findViewById<Button>(R.id.heavyBodyButton).isActivated = true
            findViewById<Button>(R.id.heavyLegButton).isActivated = true
        }
    }

    fun attackButtonPress(v : View){
        when (v.id) {
            R.id.headButton -> {
                attack = "head"
                power = 0
                findViewById<TextView>(R.id.atakaText).text = "Атака - Голова"
            }
            R.id.bodyButton -> {
                attack = "body"
                power = 0
                findViewById<TextView>(R.id.atakaText).text = "Атака - Тело"
            }
            R.id.legButton -> {
                attack = "leg"
                power = 0
                findViewById<TextView>(R.id.atakaText).text = "Атака - Ноги"
            }
            R.id.heavyHeadButton -> {
                attack = "head"
                power = 1
                findViewById<TextView>(R.id.atakaText).text = "Сильная атака - Голова"
            }
            R.id.heavyBodyButton -> {
                attack = "body"
                power = 1
                findViewById<TextView>(R.id.atakaText).text = "Сильная атака - Тело"
            }
            R.id.heavyLegButton -> {
                attack = "leg"
                power = 1
                findViewById<TextView>(R.id.atakaText).text = "Сильная атака - Ноги"
            }
            R.id.noneAtakButton ->{
                attack = "no"
                power = 0
                findViewById<TextView>(R.id.atakaText).text = "Не атаковать"
            }
        }
    }

    fun defendButtonPress(v : View){
        when (v.id) {
            R.id.defendHeadButton -> {
                defend = "head"
                findViewById<TextView>(R.id.defendText).text = "Голова"
            }
            R.id.defendBodyButton -> {
                defend = "body"
                findViewById<TextView>(R.id.defendText).text = "Тело"
            }
            R.id.defendLegButton -> {
                defend = "leg"
                findViewById<TextView>(R.id.defendText).text = "Ноги"
            }
        }
    }
    fun doneButtonPress(v: View){
        if (attack != "none" && defend != "none"){
            val result = Intent()
            result.putExtra("name", findViewById<TextView>(R.id.textView2).text)
            result.putExtra("attack", attack)
            result.putExtra("powerOfAttack", power)
            result.putExtra("defend", defend)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
        else{
            Toast.makeText(this, "Сделайте выбор", Toast.LENGTH_SHORT).show()
        }
    }
}