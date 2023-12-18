package com.dicoding.frency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cropoptima.cropoptima.data.SuggestionRecently
import com.cropoptima.cropoptima.databinding.ItemSuggestRecentlyBinding

class CarouselHomeAdapter(private val onItemClick: (SuggestionRecently) -> Unit) :
    ListAdapter<SuggestionRecently, CarouselHomeAdapter.CarouselViewHolder>(DIFF_CALLBACK) {

    inner class CarouselViewHolder(private val binding: ItemSuggestRecentlyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SuggestionRecently) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.imageUrl)
//                .diskCacheStrategy(DiskCacheStrategy.NONE )
//                .skipMemoryCache(true)
                    .into(binding.ivRecentImage)
                tvRecentLocation.text = item.location
                tvPlantName.text = item.plantResult
                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ItemSuggestRecentlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        binding.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val franchise = getItem(position)
        holder.bind(franchise)
    }

    override fun getItemCount(): Int {
        return 5
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SuggestionRecently>() {
            override fun areItemsTheSame(oldItem: SuggestionRecently, newItem: SuggestionRecently): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: SuggestionRecently, newItem: SuggestionRecently): Boolean =
                oldItem.location == newItem.location  && oldItem.imageUrl == newItem.imageUrl
        }
    }
}