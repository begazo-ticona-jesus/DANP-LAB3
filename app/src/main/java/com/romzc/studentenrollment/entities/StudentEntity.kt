package com.romzc.studentenrollment.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="studentId")
    val studentId: Int,

    @ColumnInfo(name = "studentName")
    val studentName: String,
)
