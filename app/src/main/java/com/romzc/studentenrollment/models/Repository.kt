package com.romzc.studentenrollment.models

import com.romzc.studentenrollment.entities.CourseEntity
import com.romzc.studentenrollment.entities.CourseStudentEntity
import com.romzc.studentenrollment.entities.CourseWithStudents
import com.romzc.studentenrollment.entities.StudentEntity

class Repository(private val appDatabase: AppDatabase) {
    suspend fun getAllCourses(): List<CourseEntity> {
        return appDatabase.courseDao().getAllCourses()
    }


    suspend fun getAllCoursesWithStudents(): List<CourseWithStudents> {
        return appDatabase.courseDao().getCoursesWithStudents()
    }
    suspend fun insertStudents(userList: List<StudentEntity>) {
        return appDatabase.studentDao().insert(userList)
    }

    suspend fun insertCourses(courseList: List<CourseEntity>) {
        return appDatabase.courseDao().insertCourses(courseList)
    }

    suspend fun enrollStudent(student: StudentEntity, course: CourseEntity) {
        return appDatabase
            .crossRef()
            .insertEnroll(
                CourseStudentEntity(course.courseId.toLong(),student.studentId.toLong())
            )
    }
}