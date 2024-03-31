package com.example.laba5mobile2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private lateinit var gameLogsView : ListView
    private var logsArray: MutableList<String> = mutableListOf()
    lateinit var player1: Player
    lateinit var player2: Player

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val data = intent.extras
        if (data != null) {
            player1 = data.getString("player1Name")?.let { Player(it) }!!
            player2 = data.getString("player2Name")?.let { Player(it) }!!
        }

        findViewById<TextView>(R.id.playerName1Text).text = player1.name
        findViewById<TextView>(R.id.playerName2Text).text = player2.name

        gameLogsView = findViewById(R.id.gameLogsListView)

        logsArray.add(0,"Игра началась") // Первый лог игры
        logsArray.add(0,"Первый ходит любой из игроков")

        updateAllTexts()
    }

    fun selectPlayerButtonPress(v : View){
        if (!v.isActivated) {
            val intent = Intent(this, SelectActivity::class.java)
            if (v.id == R.id.selectPlayer1Button) {
                logsArray.add(0, "${player1.name} сделал свой выбор")
                intent.putExtra("name", player1.name)
                intent.putExtra("energy", player1.energy)
            } else if (v.id == R.id.selectPlayer2Button) {
                logsArray.add(0, "${player2.name} сделал свой выбор")
                intent.putExtra("name", player2.name)
                intent.putExtra("energy", player2.energy)
            }
            v.isActivated = true
            startForResult.launch(intent)
            updateAllTexts()
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val name = intent?.getStringExtra("name")
            if (name == player1.name){
                player1.typeOfDamage = intent.getStringExtra("attack")!!
                player1.powerOfDamage = intent.getIntExtra("powerOfAttack", 0)
                player1.typeOfDefend = intent.getStringExtra("defend")!!
                player1.enable = true
            }
            else if(name == player2.name){
                player2.typeOfDamage = intent.getStringExtra("attack")!!
                player2.powerOfDamage = intent.getIntExtra("powerOfAttack", 0)
                player2.typeOfDefend = intent.getStringExtra("defend")!!
                player2.enable = true
            }
            battle()
        } else {
            // Обработать ошибку
        }
    }
    @SuppressLint("SetTextI18n")
    private fun updateAllTexts(){
        findViewById<TextView>(R.id.playerHP1Text).text = "${player1.hp} здоровья"
        findViewById<TextView>(R.id.playerEnergy1Text).text = "${player1.energy} энергии"

        findViewById<TextView>(R.id.playerHP2Text).text = "${player2.hp} здоровья"
        findViewById<TextView>(R.id.playerEnergy2Text).text = "${player2.energy} энергии"

        val adapter = ArrayAdapter(this, R.layout.list_item, logsArray)
        gameLogsView.adapter = adapter
    }

    private fun battle(){
        if(player1.enable && player2.enable){
            if (player2.typeOfDamage == "no"){
                player2.energy++
            }
            else if (player1.typeOfDefend != player2.typeOfDamage){
                player1.hp -= player2.powerOfDamage + 1
                logsArray.add(0, "${player1.name} получил ${player2.powerOfDamage + 1} урона")
            }
            else{
                logsArray.add(0, "${player1.name} защитился")
            }

            if(player1.typeOfDamage == "no"){
                player1.energy++
            }
            else if (player2.typeOfDefend != player1.typeOfDamage){
                player2.hp -= player1.powerOfDamage + 1
                logsArray.add(0, "${player2.name} получил ${player1.powerOfDamage + 1} урона")
            }
            else{
                logsArray.add(0, "${player2.name} защитился")
            }

            if (player1.typeOfDamage != "no"){
                player1.energy -= player1.powerOfDamage + 1
            }
            if (player2.typeOfDamage != "no"){
                player2.energy -= player2.powerOfDamage + 1
            }

            player1.reset()
            player2.reset()
            if (player1.hp <= 0 || player2.hp <= 0){
                val intent = Intent(this, WinActivity::class.java)
                if (player1.hp <= 0){
                    intent.putExtra("name", player1.name)
                }
                else if (player2.hp <= 0){
                    intent.putExtra("name", player2.name)
                }
                startActivity(intent)
            }
            findViewById<Button>(R.id.selectPlayer1Button).isActivated = false
            findViewById<Button>(R.id.selectPlayer2Button).isActivated = false
            updateAllTexts()
        }
    }
}