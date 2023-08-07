package com.example.myapplication

import com.example.myapplication.entity.Student

data class StudentState(
    val students: List<Student> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isAddingStudent: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
