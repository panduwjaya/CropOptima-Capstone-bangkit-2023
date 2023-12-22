package com.cropoptima.cropoptima.main.detection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentDetectionBinding
import com.cropoptima.cropoptima.main.MainActivity
import com.cropoptima.cropoptima.main.maps.MapsActivity
import com.cropoptima.cropoptima.main.setting.SettingsPreference
import com.cropoptima.cropoptima.main.setting.SettingsViewModel
import com.cropoptima.cropoptima.main.setting.SettingsViewModelFactory
import com.cropoptima.cropoptima.utils.MainViewModelFactory
import com.cropoptima.cropoptima.utils.Result
import com.cropoptima.cropoptima.utils.Utils

class DetectionFragment : Fragment() {

    private lateinit var binding: FragmentDetectionBinding
    private val factory: MainViewModelFactory by lazy {
        MainViewModelFactory.getInstance(binding.root.context)
    }

    private val detectionViewModel: DetectionViewModel by viewModels {
        factory
    }

    // dataStore
    private lateinit var settingsViewModel: SettingsViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object {
        const val EXTRA_IMG = "extra_img"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_LOCATION = "extra_"
        const val EXTRA_CROP = "extra_parcel"
        const val EXTRA_PARCEL = "extra_parcel"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsViewModel = ViewModelProvider(requireActivity(), SettingsViewModelFactory(SettingsPreference.getInstance(requireContext().dataStore))).get(
                SettingsViewModel::class.java)

        val idToken = Utils.getCurrentUserIdToken()

        binding.tvNameLocation.setOnClickListener {
            val intent= Intent(binding.root.context, MapsActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.btnDetection.setOnClickListener {
            val inputNitrogen = binding.inputNitrogen.text.toString().toFloat()
            val inputProtein = binding.inputProtein.text.toString().toFloat()
            val inputKalium = binding.inputKalium.text.toString().toFloat()
            val inputPhTanah = binding.inputPhTanah.text.toString().toFloat()
            var inputLat: Float = 0F
            var inputLon: Float = 0F

            settingsViewModel.getlat().observe(viewLifecycleOwner){
                inputLat = it.toFloat()
            }

            settingsViewModel.getlon().observe(viewLifecycleOwner){
                inputLat = it.toFloat()
            }
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