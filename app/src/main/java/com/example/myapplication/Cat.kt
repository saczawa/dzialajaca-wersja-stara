package com.example.myapplication

    class Cat(val name: String, val breed: String){



        init{
            require(name.isNotBlank()) {"Name must not be blank"}
            require(breed.isNotBlank()) {"Name must not be blank"}
        }


        fun sound() = "miaaaooowww"

        override fun toString() = "$name : $breed"

        constructor(name: String): this(name, "Moggy")
    }
