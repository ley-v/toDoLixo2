package com.example.todolixo2.ui.addedittask

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todolixo2.data.Task
import com.example.todolixo2.data.TaskDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface AddEditTasksViewModelAssistedFactory {
    fun create(savedStateHandle: SavedStateHandle): AddEditTasksViewModel
}

class AddEditTasksViewModel @AssistedInject constructor(
    private val taskDao: TaskDao,
    @Assisted private val state: SavedStateHandle
): ViewModel() {

    val task = state.get<Task>("taskKey")
    var taskName = state.get<String>("taskName") ?: task?.name ?: ""
        set(value) {
            field = value
            state.set("taskName", value)
        }
    var taskImportance = state.get<Boolean>("taskImportance") ?: task?.important ?: false
        set(value) {
            field = value
            state["taskImportance"] = value
        }

}