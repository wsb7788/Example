package com.project.navmvvmpractice.ui.home.todo

import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.navmvvmpractice.base.BaseFragment
import com.project.navmvvmpractice.R
import com.project.navmvvmpractice.data.remote.home.todo.TodoListener
import com.project.navmvvmpractice.databinding.FragmentTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TodoFragment : BaseFragment<FragmentTodoBinding>(R.layout.fragment_todo), TodoListener {

    val viewModel: TodoViewModel by viewModels()


    private val pagingAdapter by lazy { TodoPagingAdapter() }


    override fun initView() {

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.todoListener = this

        initRecycler()



    }

    private fun initRecycler(){
        binding.rcvTodo.apply {
            adapter = pagingAdapter
        }

        lifecycleScope.launch{
            viewModel.list.collectLatest {
                pagingAdapter.submitData(lifecycle,it)
            }
        }
        pagingAdapter.onClicked {
            viewModel.onItemClicked(it)
        }

    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(message: String) {
        pagingAdapter.refresh()
    }


}