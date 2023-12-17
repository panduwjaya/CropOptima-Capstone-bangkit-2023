package com.cropoptima.cropoptima.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.data.SuggestionPlantList
import com.cropoptima.cropoptima.data.SuggestionRecently
import com.cropoptima.cropoptima.data.SuggestionRecentlyList
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding
import com.dicoding.frency.ui.adapter.CarouselHomeAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }


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

        val layoutManager = GridLayoutManager(binding.root.context, 2)
        var recycler = binding.rvSuggestion
        recycler.layoutManager = layoutManager
        val adapter2 = SugestionPlantAdapter()
        adapter2.submitList(SuggestionPlantList.suggestionItemList)
        recycler.adapter = adapter2

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
}