package com.project.navmvvmpractice.util

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("isComplete")
fun checkComplete(view: TextView,complete: Boolean){
    if(complete){
        view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }else{
        view.paintFlags = view.paintFlags
    }

}
