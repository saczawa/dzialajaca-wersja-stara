package com.example.myapplication

import com.example.myapplication.entity.Student

sealed interface StudentEvent
{
    object SaveStudent: StudentEvent
    data class SetFirstName(val firstName: String): StudentEvent
    data class SetLastName(val lastName: String): StudentEvent
    data class SetPhoneNumber(val phoneNumber: String): StudentEvent
    object ShowDialog: StudentEvent
    object HideDialog: StudentEvent
    data class SortStudent(val sortType: SortType): StudentEvent
    data class DeleteStudent(val student: Student): StudentEvent
}