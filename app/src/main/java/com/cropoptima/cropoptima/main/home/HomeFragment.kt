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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.data.SuggestionPlantList
import com.cropoptima.cropoptima.data.SuggestionRecently
import com.cropoptima.cropoptima.data.SuggestionRecentlyList
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding
import com.cropoptima.cropoptima.main.setting.SettingsPreference
import com.cropoptima.cropoptima.main.setting.SettingsViewModel
import com.cropoptima.cropoptima.main.setting.SettingsViewModelFactory
import com.cropoptima.cropoptima.utils.Utils
import com.dicoding.frency.ui.adapter.CarouselHomeAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }
    private lateinit var settingsViewModel: SettingsViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        Log.i("info", SuggestionPlantList.suggestionItemList.count().toString())

        carouselHomeAdapter.submitList(SuggestionRecentlyList.suggestionRecentlyList)

        with(binding) {
            this.carouselPager.apply {
                adapter = carouselHomeAdapter
                dotsIndicator.attachTo(this)
            }
        }

        return root
    }

    private fun carouselItemClicked(suggestionRecently: SuggestionRecently) {
        Toast.makeText(binding.root.context, suggestionRecently.location, Toast.LENGTH_SHORT).show()
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