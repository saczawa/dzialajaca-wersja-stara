package com.example.myapplication

fun fac(count: Int, processor: (Int, Int) ->Unit) :Int {
    var value = 1
    for (index in 1 ..count) {
        value *= index
        processor(index,value)
    }
    return value
}

fun mapper(count:Int, processor:(Int,Int)->Int):Map<Int,Int>{
    var value =1
    var values = mutableMapOf<Int, Int>()

    for(index in 1 .. count){
        value = processor(index,value*index)
        values[index] = value
    }
    return values
}





class Dice {
    val rolledvalue
        get() = (1 ..6).shuffled().first()

}
fun main(){

}