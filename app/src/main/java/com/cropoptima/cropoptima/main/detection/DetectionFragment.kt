package com.cropoptima.cropoptima.main.detection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentDetectionBinding
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding

class DetectionFragment : Fragment() {

    private lateinit var binding: FragmentDetectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inputNitrogen = binding.textInputNitrogen
        val inputProtein = binding.textInputProtein
        val inputKalium = binding.textInputKalium
        val inputPhTanah = binding.textInputPhTanah



        binding.btnDetection.setOnClickListener {

        }
    }
}