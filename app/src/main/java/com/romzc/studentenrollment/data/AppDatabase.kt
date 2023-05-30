package com.romzc.studentenrollment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.romzc.studentenrollment.data.dao.CourseDao
import com.romzc.studentenrollment.data.dao.CourseStudentDao
import com.romzc.studentenrollment.data.dao.StudentDao
import com.romzc.studentenrollment.data.entities.CourseEntity
import com.romzc.studentenrollment.data.entities.CourseStudentEntity
import com.romzc.studentenrollment.data.entities.StudentEntity

@Database(
    entities = [CourseEntity::class, StudentEntity::class, CourseStudentEntity::class],
    version = 2
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun crossRef(): CourseStudentDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "student_course"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}