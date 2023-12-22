package com.cropoptima.cropoptima.main.detection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentDetectionBinding
import com.cropoptima.cropoptima.utils.MainViewModelFactory
import com.cropoptima.cropoptima.utils.Result
import com.cropoptima.cropoptima.utils.Utils

class DetectionFragment : Fragment() {

    private lateinit var binding: FragmentDetectionBinding
    private val factory: MainViewModelFactory by lazy {
        MainViewModelFactory.getInstance(requireActivity())
    }

    private val detectionViewModel: DetectionViewModel by viewModels {
        factory
    }

    companion object {
        const val EXTRA_IMG = "extra_img"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_LOCATION = "extra_"
        const val EXTRA_CROP = "extra_parcel"
    }

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

        val inputNitrogen = binding.inputNitrogen as Float
        val inputProtein = binding.inputProtein as Float
        val inputKalium = binding.inputKalium as Float
        val inputPhTanah = binding.inputPhTanah as Float
        val inputLat: Float = 0F
        val inputLon: Float = 0F
        val idToken = Utils.getCurrentUserIdToken()

        binding.btnDetection.setOnClickListener {
            detectionViewModel.postPredict(
                idToken,
                inputNitrogen,
                inputProtein,
                inputKalium,
                inputPhTanah,
                inputLat,
                inputLon
            ).observe(viewLifecycleOwner) {result->
                when (result) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        val mBundle = Bundle()
                        mBundle.putString(EXTRA_IMG,result.data.message?.imageURL)
                        mBundle.putString(EXTRA_DESCRIPTION,result.data.message?.description)
                        mBundle.putString(EXTRA_LOCATION,result.data.message?.location)
                        mBundle.putString(EXTRA_CROP,result.data.message?.crop)
                        Toast.makeText(context, "Detection is success", Toast.LENGTH_LONG).show()
                        view?.findNavController()?.navigate(R.id.action_detection_to_resultFragment,mBundle)
                    }

                    is Result.Error -> {
                        Toast.makeText(context, result.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}