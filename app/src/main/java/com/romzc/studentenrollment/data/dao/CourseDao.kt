package com.romzc.studentenrollment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.romzc.studentenrollment.data.entities.CourseEntity
import com.romzc.studentenrollment.data.entities.CourseWithStudents
import com.romzc.studentenrollment.data.entities.StudentEntity

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course ORDER BY CourseId")
    suspend fun getAllCourses(): List<CourseEntity>

   @Transaction
   @Query("SELECT * FROM Course")
   fun getCoursesWithStudents(): List<CourseWithStudents>

    @Insert
    suspend fun insert(course: CourseEntity)

    @Insert
    suspend fun insertCourses(coursesList: List<CourseEntity>)

}