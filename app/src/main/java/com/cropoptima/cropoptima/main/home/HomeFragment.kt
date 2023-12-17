package com.cropoptima.cropoptima.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.data.SuggestionItemList
import com.cropoptima.cropoptima.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

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
            Navigation.findNavController(root).navigate(R.id.action_home_to_setting)
        }

        val layoutManager = GridLayoutManager(binding.root.context, 2)
        var recycler = binding.rvSuggestion
        recycler.layoutManager = layoutManager
        val adapter = SugestionItemAdapter()
        adapter.submitList(SuggestionItemList.suggestionItemList)
        recycler.adapter = adapter


        return root
    }

}