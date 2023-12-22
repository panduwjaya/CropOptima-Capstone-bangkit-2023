package com.cropoptima.cropoptima.main.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentResultBinding
import com.cropoptima.cropoptima.main.detection.DetectionFragment

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data
        val dataImg = arguments?.getString(DetectionFragment.EXTRA_IMG)
        val dataDescription = arguments?.getString(DetectionFragment.EXTRA_DESCRIPTION)
        val dataLocation = arguments?.getString(DetectionFragment.EXTRA_LOCATION)
        val dataCrop = arguments?.getString(DetectionFragment.EXTRA_CROP)


        binding.imgArrowBackResult.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_resultFragment_to_detection)
        }

        binding.tvLokasi.text = dataLocation
        Glide.with(requireActivity())
            .load(dataImg)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivSayur)
        binding.tvNamaSayur.text = dataCrop
        binding.tvKeterangan.text = dataDescription
    }
}