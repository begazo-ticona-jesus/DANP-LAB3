package com.romzc.studentenrollment.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Course")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="courseId")
    val courseId: Int,

    @ColumnInfo(name="courseName")
    val courseName: String
)
