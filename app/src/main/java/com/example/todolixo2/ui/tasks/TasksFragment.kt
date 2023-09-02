package com.example.todolixo2.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolixo2.R
import com.example.todolixo2.data.SortOrder
import com.example.todolixo2.data.Task
import com.example.todolixo2.databinding.FragmentTasksBinding
import com.example.todolixo2.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks), TasksAdapter.OnItemClickListenerTA {

    private val viewModel: TasksViewModel by viewModels()
    private lateinit var binding: FragmentTasksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setupToolbar()

        val tasksAdapter = TasksAdapter(this)

        binding.apply {
            rvTask.apply {
                adapter = tasksAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner, object : Observer<List<Task>?> {
            override fun onChanged(value: List<Task>?) {
                tasksAdapter.submitList(value)
            }
        })

    }

    override fun onItemCLick(task: Task) {
        viewModel.onTaskSelected(task)
    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        viewModel.onTaskCheckedChanged(task, isChecked)
    }

    //configurando a toolbar (estou usando a actionbar pois não consegui deixar a flecha na cor branca)
    private fun setupToolbar() {
//        binding.toolbar.apply {
//            overflowIcon?.setTint(Color.WHITE)
//        }
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_tasks, menu)

                val searchItem = menu.findItem(R.id.mnActionSearch)
                val searchView = searchItem.actionView as SearchView

//                val searchColor = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)

//                val searchViewCloseIcon: ImageView =
//                    searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
//                searchViewCloseIcon.setColorFilter(
//                    ContextCompat.getColor(requireContext(), R.color.white),
//                    android.graphics.PorterDuff.Mode.SRC_IN
//                )

//                searchItem.icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.RED, BlendModeCompat.SRC_ATOP)

                searchView.onQueryTextChanged {
                    viewModel.searchQuery.value = it
                }

                viewLifecycleOwner.lifecycleScope.launch {
                    menu.findItem(R.id.mnActionHideCompletedTasks).isChecked =
                        viewModel.preferencesFlow.first().hideCompleted
                }
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.mnActionSortByName -> {
                        viewModel.onSortOrderSelected(SortOrder.BY_NAME)
                    }

                    R.id.mnActionSortByDateCreated -> {
                        viewModel.onSortOrderSelected(SortOrder.BY_DATE)
                    }

                    R.id.mnActionHideCompletedTasks -> {
                        item.isChecked = !item.isChecked
                        viewModel.onHideCompletedClick(item.isChecked)
                    }

                    R.id.mnActionDeleteAllCompletedTasks -> {

                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}