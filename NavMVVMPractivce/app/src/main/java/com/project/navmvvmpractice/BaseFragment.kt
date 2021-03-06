package com.project.navmvvmpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class BaseFragment < B:ViewDataBinding>(@LayoutRes private val layoutId: Int): Fragment() {

    protected lateinit var binding : B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutId, container, false)

        initView()
        return binding.root

    }

    abstract fun initView()

}