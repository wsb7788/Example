package com.project.movieapp.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.movieapp.MovieViewModel
import com.project.movieapp.R
import com.project.movieapp.databinding.FragmentDetailsBinding
import com.project.movieapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {


    lateinit var binding: FragmentDetailsBinding

    val args : DetailsFragmentArgs by navArgs()

    val viewModel:MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.backPress.setOnClickListener{
            findNavController().popBackStack()
        }

        viewModel.getMovieDetails(args.imdbId!!)

        viewModel.movieDetails.observe(viewLifecycleOwner){
            when(it.getContentIfNotHandled()?.status){
                Status.LOADING ->{
                    binding.detailsProgress.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    binding.detailsProgress.visibility = View.GONE

                }
                Status.SUCCESS ->{
                    binding.detailsProgress.visibility = View.GONE

                    binding.movieDetails = it.peekContent().data

                }
                else ->{

                }
            }
        }
    }
}

