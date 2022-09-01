package com.project.navmvvmpractice.ui.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.project.navmvvmpractice.base.BaseFragment
import com.project.navmvvmpractice.R
import com.project.navmvvmpractice.data.remote.login.SignUpListener
import com.project.navmvvmpractice.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up), SignUpListener {


    val viewModel : LoginViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.signUpListener = this


    }

    override fun onCancelClicked() {
        findNavController().popBackStack()
    }

    override fun onSuccess() {
        findNavController().popBackStack()
    }

    override fun onFailure(text: String) {
        Toast.makeText(requireContext(),text, Toast.LENGTH_SHORT).show()

    }



}