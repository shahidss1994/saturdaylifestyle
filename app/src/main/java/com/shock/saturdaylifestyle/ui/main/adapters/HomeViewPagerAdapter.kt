package com.shock.saturdaylifestyle.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemHomeViewpagerBinding
import com.shock.saturdaylifestyle.ui.main.models.HomeViewPagerDM

class HomeViewPagerAdapter(private val dataList: List<HomeViewPagerDM>) : RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {
    class ViewHolder private constructor(private val binding: ItemHomeViewpagerBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemHomeViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(data: HomeViewPagerDM) {
            binding.apply {
                data.image?.let { ivBackground.setBackgroundResource(it) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}