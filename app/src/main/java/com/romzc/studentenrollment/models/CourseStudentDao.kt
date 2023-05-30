package com.romzc.studentenrollment.models

import androidx.room.Dao
import androidx.room.Insert
import com.romzc.studentenrollment.entities.CourseStudentEntity

@Dao
interface CourseStudentDao {
    @Insert
    suspend fun insertEnroll(courseStudentEntity: CourseStudentEntity)

}