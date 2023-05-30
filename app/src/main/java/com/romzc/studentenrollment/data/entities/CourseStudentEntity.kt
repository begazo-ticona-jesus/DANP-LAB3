package com.romzc.studentenrollment.data.entities

import androidx.room.Entity

@Entity(
    primaryKeys = ["courseId", "studentId"]
)
data class CourseStudentEntity (
    val courseId: Long,
    val studentId: Long
)