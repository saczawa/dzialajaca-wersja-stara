package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Upsert
    suspend fun upsertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student ORDER BY firstName ASC")
    fun getStudentsOrderedByFirstName(): Flow<List<Student>>

    @Query("SELECT * FROM student ORDER BY lastName ASC")
    fun getStudentsOrderedByLastName(): Flow<List<Student>>

    @Query("SELECT * FROM student ORDER BY phoneNumber ASC")
    fun getStudentsOrderedByPhoneNumber(): Flow<List<Student>>
}