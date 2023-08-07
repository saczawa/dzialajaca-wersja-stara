package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)