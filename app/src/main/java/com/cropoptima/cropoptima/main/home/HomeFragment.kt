package com.cropoptima.cropoptima.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cropoptima.cropoptima.utils.Result
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.data.SuggestionPlantList
import com.cropoptima.cropoptima.data.SuggestionRecently
import com.cropoptima.cropoptima.data.SuggestionRecentlyList
import com.cropoptima.cropoptima.data.network.response.HistoriesItem
import com.cropoptima.cropoptima.data.network.response.Message
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding
import com.cropoptima.cropoptima.main.detection.DetectionFragment
import com.cropoptima.cropoptima.main.setting.SettingsPreference
import com.cropoptima.cropoptima.main.setting.SettingsViewModel
import com.cropoptima.cropoptima.main.setting.SettingsViewModelFactory
import com.cropoptima.cropoptima.utils.MainViewModelFactory
import com.dicoding.frency.ui.adapter.CarouselHomeAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }
    private lateinit var settingsViewModel: SettingsViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val factory: MainViewModelFactory by lazy {
        MainViewModelFactory.getInstance(binding.root.context)
    }

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loadHistoryData()
        binding.ivSetting.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_setting)
        }

        settingsViewModel =
            ViewModelProvider(requireActivity(), SettingsViewModelFactory(SettingsPreference.getInstance(requireContext().dataStore))).get(
                SettingsViewModel::class.java
            )

        checkSavedTheme()

        val layoutManager = GridLayoutManager(binding.root.context, 2)
        val recycler = binding.rvSuggestion
        recycler.layoutManager = layoutManager
        val adapter2 = SugestionPlantAdapter()
        adapter2.submitList(SuggestionPlantList.suggestionItemList.shuffled())
        recycler.adapter = adapter2

        return root
    }

    private fun loadHistoryData() {
        val user = Firebase.auth.currentUser
        user?.getIdToken(true)?.addOnCompleteListener {
            homeViewModel.postHistory(it.result.token!!).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        // Handle loading state
//                        binding.pbListFranchise.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
//                        binding.pbListFranchise.visibility = View.GONE
                        val dataHistory = result.data.histories
                        if (dataHistory != null) {
                            // Tampilkan data di RecyclerView
                            Log.d("data", dataHistory.toString())
                            carouselHomeAdapter.submitList(dataHistory)

                            with(binding) {
                                this.carouselPager.apply {
                                    adapter = carouselHomeAdapter

                                    dotsIndicator.attachTo(this)
                                }
                            }
                        } else {
                            // Tampilkan pesan jika tidak ada data
//                            binding.tvNoData.visibility = View.VISIBLE
                        }
                    }

                    is Result.Error -> {
                        // Handle error state
//                        binding.pbListFranchise.visibility = View.GONE
                    }

                    else -> {}
                }

            }
        }
    }

    private fun carouselItemClicked(historiesItem: HistoriesItem) {
        val message = Message(historiesItem.imageURL, historiesItem.description,
            historiesItem.location, historiesItem.crop)
        val bundle = Bundle()
        bundle.putParcelable(DetectionFragment.EXTRA_CROP, message)
        findNavController().navigate(R.id.action_home_to_resultFragment, bundle)
    }

    private fun checkSavedTheme() {
        settingsViewModel.getThemeSettings().observe(viewLifecycleOwner) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}