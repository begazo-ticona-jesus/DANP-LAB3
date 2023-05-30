package com.romzc.studentenrollment.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
    @Embedded val courseEntity: CourseEntity,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(CourseStudentEntity::class)
    )
    val students: List<StudentEntity>
)
