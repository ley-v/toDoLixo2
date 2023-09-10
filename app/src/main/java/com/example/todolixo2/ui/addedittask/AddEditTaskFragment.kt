package com.example.todolixo2.ui.addedittask

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolixo2.R
import com.example.todolixo2.databinding.FragmentAddEditTaskBinding
import com.example.todolixo2.ui.AddEditTasksViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddEditTaskFragment: Fragment(R.layout.fragment_add_edit_task) {

    @Inject
    lateinit var viewModelFactory: AddEditTasksViewModelFactory
    private val viewModel by viewModels<AddEditTasksViewModel> { viewModelFactory }
//private val viewModel: AddEditTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    val binding = FragmentAddEditTaskBinding.bind(view)
binding.apply {
    edtTaskName.setText(viewModel.taskName)
    cbImportant.isChecked = viewModel.taskImportance
    cbImportant.jumpDrawablesToCurrentState()
    tvDateCreated.isVisible = viewModel.task != null
    tvDateCreated.text = "Created: ${viewModel.task?.createdDateFormatted}"
}

    }
}