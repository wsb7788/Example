package com.project.navmvvmpractice.ui.login

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.navmvvmpractice.data.entites.User
import com.project.navmvvmpractice.data.remote.login.LoginListener
import com.project.navmvvmpractice.data.remote.login.SignUpListener
import com.project.navmvvmpractice.room.dao.Database
import com.project.navmvvmpractice.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(private val userDatabase:Database, private val dataStoreManager: DataStoreManager) : ViewModel() {

    var loginListener: LoginListener? = null
    var signUpListener: SignUpListener? = null


    val id = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")


    fun onLoginClicked() {
        if(id.value.isNullOrEmpty()){
            signUpListener?.onFailure("아이디를 입력하세요")
            return
        }
        if(pw.value.isNullOrEmpty()){
            signUpListener?.onFailure("비밀번호를 입력하세요")
            return
        }
        viewModelScope.launch {
            try {
                val id = id.value
                val pw = pw.value
                val result = userDatabase.userDao().findId(id!!)
                if(result != null && result.pw == pw!!){
                    dataStoreManager.saveId(id)
                    loginListener?.onLoginSuccess()
                    return@launch
                }
                loginListener?.onLoginFailure("아이디 및 비밀번호가 일치하지 않습니다.")
            }
            catch (e: Exception){
                loginListener?.onLoginFailure(e.message!!)
            }
        }
    }

    fun onSignInClicked() {
        loginListener!!.onSignUpClicked()
    }

    fun onConfirmClicked(){
        if(id.value.isNullOrEmpty()){
            signUpListener?.onFailure("아이디를 입력하세요")
            return
        }
        if(pw.value.isNullOrEmpty()){
            signUpListener?.onFailure("비밀번호를 입력하세요")
            return
        }
        viewModelScope.launch {
            try {
                val id = id.value
                val pw = pw.value
                val result = userDatabase.userDao().findId(id!!)
                result.let {
                    userDatabase.userDao().insert(User(id!!,pw!!))
                    signUpListener!!.onSuccess()
                    return@launch
                }
                signUpListener?.onFailure("아이디가 이미 존재합니다.")
            }catch (e:Exception){
                signUpListener?.onFailure(e.message!!)
            }
        }

    }
    fun onCancelClicked(){
        signUpListener?.onCancelClicked()
    }

}