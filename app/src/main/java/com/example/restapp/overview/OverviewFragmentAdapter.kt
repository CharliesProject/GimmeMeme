package com.example.restapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restapp.databinding.SingleItemViewBinding
import com.example.restapp.network.SinglePhoto

class OverviewFragmentAdapter : ListAdapter<SinglePhoto,
        OverviewFragmentAdapter.SinglePhotoViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<SinglePhoto>() {
        override fun areItemsTheSame(oldItem: SinglePhoto, newItem: SinglePhoto): Boolean {
            // return oldItem.id == newItem.id
            return oldItem.ups == newItem.ups
        }
        override fun areContentsTheSame(oldItem: SinglePhoto, newItem: SinglePhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    class SinglePhotoViewHolder(private var binding: SingleItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(SinglePhoto: SinglePhoto) {
            binding.photo = SinglePhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewFragmentAdapter.SinglePhotoViewHolder {
        return SinglePhotoViewHolder(SingleItemViewBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OverviewFragmentAdapter.SinglePhotoViewHolder, position: Int) {
        val singlePhoto = getItem(position)
        holder.bind(singlePhoto)

    }
}