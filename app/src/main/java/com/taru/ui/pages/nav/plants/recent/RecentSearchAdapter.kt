package com.taru.ui.pages.nav.plants.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.taru.R
import com.taru.databinding.HomeCategoryItemBinding
import com.taru.databinding.NavPlantsRecentItemBinding

/**
 * Created by Niraj on 10-01-2023.
 */
class RecentSearchAdapter : ListAdapter<ModelRecent, RecentSearchAdapter.ItemViewHolder>(ModelRecent.diffCallback) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return viewHolder(parent, this)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(var binding: NavPlantsRecentItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: ModelRecent) {
            binding.navPlantsRecentItemImage.load(R.drawable.pic_tool_category)

        }


    }

    companion object {
        fun viewHolder(parent: ViewGroup, adapter: RecentSearchAdapter): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NavPlantsRecentItemBinding.inflate(layoutInflater, parent, false)
            return adapter.ItemViewHolder(binding)
        }
    }
}