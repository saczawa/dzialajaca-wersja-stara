package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dao.StudentDao
import com.example.myapplication.entity.Student

@Database(
    entities = [Student::class],
    version = 1
)
abstract class StudentDatabase: RoomDatabase() {

    abstract val dao: StudentDao
}