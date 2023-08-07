package com.example.myapplication

class Pets(val num: Int){

    private val myPets = mutableListOf<Cat>()
    private var numPets = 0

    init{
        require(num > 0) {"number must be greater than 0"}
    }


    fun showSound(){
        for(myPet in myPets){
            println("${myPet.name} goes ${myPet.sound()}")
        }
    }
}