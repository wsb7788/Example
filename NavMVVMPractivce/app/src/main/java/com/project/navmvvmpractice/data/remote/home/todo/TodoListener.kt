package com.project.navmvvmpractice.data.remote.home.todo

interface TodoListener {
    abstract fun onFailure(message:String)
    abstract fun onSuccess(message:String)
}