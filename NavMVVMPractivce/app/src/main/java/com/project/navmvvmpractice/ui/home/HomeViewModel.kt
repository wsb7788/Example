package com.project.navmvvmpractice.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.navmvvmpractice.data.remote.home.HomeListener
import com.project.navmvvmpractice.data.uiState.HomeUiState
import com.project.navmvvmpractice.room.dao.Database
import com.project.navmvvmpractice.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataStoreManager: DataStoreManager):ViewModel() {

    var homeListener : HomeListener? = null
    var isExpended = MutableLiveData<Boolean>(false)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState:StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun onPlusClicked(){
        val value = isExpended.value
        isExpended.value = !value!!
    }
     fun onTodoClicked(){
         val value = isExpended.value
         homeListener!!.onTodoClicked()
         isExpended.value = !value!!

     }
     fun onAlarmClicked(){
         val value = isExpended.value
        homeListener!!.onAlarmClicked()
         isExpended.value = !value!!
    }

    fun loadUiState() {
        viewModelScope.launch {
            _uiState.update {
                val id = dataStoreManager.getId()
                it.copy( id= id )
            }

        }


    }


}