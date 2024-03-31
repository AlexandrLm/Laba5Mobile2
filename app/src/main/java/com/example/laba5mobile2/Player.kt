package com.example.laba5mobile2

class Player(_name: String){
    var name : String
    init {
        name = _name
    }
    var hp : Int = 10
    var energy : Int = 5
    var powerOfDamage = 0 // 0 - обычная атака 1 - сильная отака
    lateinit var typeOfDamage : String
    lateinit var typeOfDefend : String
    var enable : Boolean = false

    fun reset(){
        powerOfDamage = 0
        typeOfDamage = "none"
        typeOfDefend = "none"
        enable = false
    }
}