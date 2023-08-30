package com.binargrouptwo.orpheum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binargrouptwo.orpheum.databinding.ItemSpotifyRvBinding
import com.binargrouptwo.orpheum.model.entities.browse.ItemData
import com.binargrouptwo.orpheum.model.entities.browse.NewReleaseResponse
import com.bumptech.glide.Glide


class SpotifyAdapter :
    RecyclerView.Adapter<SpotifyAdapter.SpotifyHolder>() {
    lateinit var listNewRelease: List<ItemData>

    fun setData(listNewRelease: List<ItemData>) {
        this.listNewRelease = listNewRelease
    }

    class SpotifyHolder(private val binding: ItemSpotifyRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listNewRelease: ItemData) {
            binding.apply {
                tvNameTitle.text = listNewRelease.name
                tvArtist.text = listNewRelease.artists.toString()
                Glide.with(root)
                    .load(listNewRelease.images[1])
                    .into(ivTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotifyHolder {
        return SpotifyHolder(ItemSpotifyRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SpotifyHolder, position: Int) =
        holder.bindData(listNewRelease[position])


    override fun getItemCount(): Int = listNewRelease.size
}