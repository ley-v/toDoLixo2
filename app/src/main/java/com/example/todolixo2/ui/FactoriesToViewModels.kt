package com.example.todolixo2.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.todolixo2.ui.addedittask.AddEditTasksViewModelAssistedFactory
import com.example.todolixo2.ui.tasks.TasksViewModelAssistedFactory
import javax.inject.Inject


class TasksViewModelFactory @Inject constructor(
    private val assistedFactory: TasksViewModelAssistedFactory,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")
        return assistedFactory.create(handle) as T
    }
}

class AddEditTasksViewModelFactory @Inject constructor(
    private val assistedFactory: AddEditTasksViewModelAssistedFactory,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")
        return assistedFactory.create(handle) as T
    }
}



