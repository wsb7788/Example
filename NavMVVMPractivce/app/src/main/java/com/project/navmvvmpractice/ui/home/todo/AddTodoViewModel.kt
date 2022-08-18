package com.project.navmvvmpractice.ui.home.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.navmvvmpractice.data.entites.Todo
import com.project.navmvvmpractice.data.remote.home.todo.AddTodoListener
import com.project.navmvvmpractice.room.dao.Database
import com.project.navmvvmpractice.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(private val database: Database, private val dataStoreManager: DataStoreManager):ViewModel() {

    var todo = MutableLiveData<String>("")
    var addTodoListener : AddTodoListener? = null

    fun onCancelClicked(){
        addTodoListener!!.onCancelClicked()
    }
    fun onConfirmClicked(){
        viewModelScope.launch {
            val todo = todo.value
            if(todo.isNullOrEmpty()){
                addTodoListener!!.onFailure("내용을 입력하세요.")
                return@launch
            }


            try {
                val id = dataStoreManager.getId()
                database.todoDao().insert(Todo(id = id,task = todo))
                addTodoListener!!.onSuccess()
            }catch (e:Exception){
                addTodoListener!!.onFailure(e.message!!)
            }
        }

    }

}