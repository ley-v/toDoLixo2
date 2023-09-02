package com.example.todolixo2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolixo2.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = false )
abstract class TaskDatabase: RoomDatabase(){

    abstract fun taskDao(): TaskDao

    class CallbackX @Inject constructor(
        private val database: Provider<TaskDatabase>,
@ApplicationScope private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()
applicationScope.launch {
    dao.insert(Task(name = "f mt"))
    dao.insert(Task(name = "f jp", important = true))
    dao.insert(Task(name = "f rs", important = true))
    dao.insert(Task(name = "f alv"))
    dao.insert(Task(name = "f pisarat"))
    dao.insert(Task(name = "f seha"))
    dao.insert(Task(name = "f jina", completed = true))
    dao.insert(Task(name = "f suzu"))
    dao.insert(Task(name = "f violet", completed = true))
}

        }

    }
}