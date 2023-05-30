package com.romzc.studentenrollment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.romzc.studentenrollment.data.AppDatabase
import com.romzc.studentenrollment.data.Repository
import com.romzc.studentenrollment.data.entities.CourseEntity
import com.romzc.studentenrollment.data.entities.CourseWithStudents
import com.romzc.studentenrollment.data.entities.StudentEntity
import com.romzc.studentenrollment.ui.theme.StudentEnrollmentTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val coroutineScope = CoroutineScope((Dispatchers.Default))

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            StudentEnrollmentTheme {
                val screenState = remember { mutableStateOf("FILL") }
                // A surface container using the 'background' color from the theme
                val coursestudents = remember { mutableStateOf<List<CourseWithStudents>?>(null) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    val repository = Repository(AppDatabase.getInstance(context.applicationContext))
                    val scope = rememberCoroutineScope()



                if(screenState.value == "FILL") {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { fillTables(repository, scope) }) {
                            Text(text = "Fill Tables")
                        }

                        Button(onClick = { screenState.value = "VISUAL" }) {
                            Text(text = "Visualize")
                        }
                    }
                }
                else {
                    LaunchedEffect(Unit) {
                        coroutineScope.launch {
                            val list = repository.getAllCoursesWithStudents()
                            coursestudents.value = list
                        }
                    }
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { screenState.value = "FILL" }) {
                            Text(text = "Back")
                        }
                        if (coursestudents.value != null) {
                            VisulizeScreen(coursestudents = coursestudents.value!!)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun VisulizeScreen(coursestudents: List<CourseWithStudents>) {
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)) {
            items(coursestudents) { courseStudent ->
                Spacer(modifier = Modifier.height(12.dp))
                Card(backgroundColor = Color.Magenta) {
                    Box(Modifier.padding(10.dp)) {
                        Text(text = courseStudent.courseEntity.courseName)
                        Column(Modifier.padding(top = 26.dp)) {
                            courseStudent.students.map { student ->
                                Text(text = student.studentName)
                            }
                        }
                    }
                }
            }
        }
    }
}


fun fillTables(rep: Repository, scope: CoroutineScope) {
    val courses = ArrayList<CourseEntity>()
    val students = ArrayList<StudentEntity>()

    for (i in 20..29) {
        students.add(StudentEntity(i, studentName = "Name $i ${i+3}"))
    }

    scope.launch {
        rep.insertStudents(students)
    }

    for (i in 1..6) {
        courses.add(CourseEntity(i, courseName = "Course $i - Name"))
    }
    scope.launch {
        rep.insertCourses(courses)
    }

    for( i in students ) {
        for (j in 1..3) {
            scope.launch {
                rep.enrollStudent(i, courses[j])
            }
        }
    }
}