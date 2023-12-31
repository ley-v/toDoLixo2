package com.example.todolixo2.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
//import kotlinx.parcelize.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val createdTime: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable {

    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(createdTime)
}
