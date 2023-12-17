package com.cropoptima.cropoptima.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cropoptima.cropoptima.data.SuggestionPlant
import com.cropoptima.cropoptima.databinding.SuggestionRvItemBinding

class SugestionPlantAdapter : ListAdapter<SuggestionPlant, SugestionPlantAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SuggestionRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val suggestionItem = getItem(position) as SuggestionPlant
        //TODO 9 : Bind data to ViewHolder (You can run app to check)
        holder.bind(suggestionItem)
    }

    class MyViewHolder(private val binding: SuggestionRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SuggestionPlant) {
            binding.tvName.text = item.name
            binding.tvDesc.text = item.desc
            Glide.with(binding.root)
                .load(item.photoUrl)
//                .diskCacheStrategy(DiskCacheStrategy.NONE )
//                .skipMemoryCache(true)
                .into(binding.imageView5)


//            binding.root.setOnClickListener {
//                val context = binding.root.context
//                val intent = Intent(context, DetailUserActivity::class.java)
//                intent.putExtra(PARCEL_NAME, item)
//                context.startActivity(intent)
//            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SuggestionPlant>() {
            override fun areItemsTheSame(oldItem: SuggestionPlant, newItem: SuggestionPlant): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: SuggestionPlant, newItem: SuggestionPlant): Boolean {
                return oldItem == newItem
            }
        }
        const val PARCEL_NAME = "data"
    }
}