package com.cropoptima.cropoptima.auth.wellcome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentWellcomeBinding

class WellcomeFragment : Fragment() {
    private lateinit var binding: FragmentWellcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWellcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_wellcomeFragment_to_registerFragment)
        }
        binding.tvIHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_wellcomeFragment_to_loginFragment)
        }
    }

}