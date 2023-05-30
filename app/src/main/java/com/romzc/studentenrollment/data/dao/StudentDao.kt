package com.romzc.studentenrollment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.romzc.studentenrollment.data.entities.CourseEntity
import com.romzc.studentenrollment.data.entities.StudentEntity

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student ORDER BY StudentId")
    suspend fun getAllStudents(): List<StudentEntity>

    @Insert
    suspend fun insert(studentsEntity: List<StudentEntity>)

}