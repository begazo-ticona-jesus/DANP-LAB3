package com.romzc.studentenrollment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.romzc.studentenrollment.data.entities.CourseEntity
import com.romzc.studentenrollment.data.entities.CourseStudentEntity
import com.romzc.studentenrollment.data.entities.StudentEntity

@Dao
interface CourseStudentDao {
    @Insert
    suspend fun insertEnroll(courseStudentEntity: CourseStudentEntity)

}