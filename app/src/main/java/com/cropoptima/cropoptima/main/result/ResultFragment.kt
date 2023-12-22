package com.cropoptima.cropoptima.main.result

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.data.network.response.Message
import com.cropoptima.cropoptima.databinding.FragmentResultBinding
import com.cropoptima.cropoptima.main.detection.DetectionFragment

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataResult: Message

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            dataResult = arguments?.getParcelable(DetectionFragment.EXTRA_CROP, Message::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            dataResult = arguments?.getParcelable(DetectionFragment.EXTRA_CROP)!!
        }

        // get data
        val dataImg = dataResult.imageURL
        val dataDescription = dataResult.description
        val dataLocation = dataResult.location
        val dataCrop = dataResult.crop

        binding.imgArrowBackResult.setOnClickListener {
//            view?.findNavController()?.navigate(R.id.action_resultFragment_to_detection)
            findNavController().navigateUp()
        }

        binding.tvLokasi.text = dataLocation
        Glide.with(requireActivity())
            .load(dataImg)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivSayur)
        binding.tvNamaSayur.text = dataCrop
        binding.tvKeterangan.text = dataDescription
    }
}