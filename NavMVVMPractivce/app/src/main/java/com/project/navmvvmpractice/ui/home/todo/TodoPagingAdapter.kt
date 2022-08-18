package com.project.navmvvmpractice.ui.home.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.navmvvmpractice.BR
import com.project.navmvvmpractice.data.entites.Todo
import com.project.navmvvmpractice.databinding.ViewHolderTodoBinding

class TodoPagingAdapter: PagingDataAdapter<Todo, TodoPagingAdapter.TodoViewHolder>(DIFF_UTIL) {

    var onClick: ((Todo) -> Unit)? = null


    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.index == newItem.index
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }

        }
    }
    fun onClicked(listener: (Todo) -> Unit){
        onClick = listener
    }

    inner class TodoViewHolder(val viewDataBinding: ViewHolderTodoBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
            fun bind(data:Todo){
                viewDataBinding.todo = data
            }
        }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }


        holder.viewDataBinding.root.setOnClickListener {
            onClick?.let{
                data?.let{
                    it(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ViewHolderTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }
}