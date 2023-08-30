package com.binargrouptwo.orpheum.model.entities.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binargrouptwo.orpheum.databinding.ItemNewReleaseBinding
import com.binargrouptwo.orpheum.model.entities.browse.ItemData
import com.bumptech.glide.Glide


class NewReleaseAdapter :
    RecyclerView.Adapter<NewReleaseAdapter.SpotifyHolder>() {
    lateinit var listNewRelease: List<ItemData>

    fun setData(listNewRelease: List<ItemData>) {
        this.listNewRelease = listNewRelease
    }

    class SpotifyHolder(private val binding: ItemNewReleaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listNewRelease: ItemData) {
            binding.apply {
                tvTitle.text = listNewRelease.name
                tvArtist.text = listNewRelease.artists[0].name
                Glide.with(root)
                    .load(listNewRelease.images[0].url)
                    .into(ivTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotifyHolder {
        return SpotifyHolder(ItemNewReleaseBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SpotifyHolder, position: Int) =
        holder.bindData(listNewRelease[position])


    override fun getItemCount(): Int = listNewRelease.size
}