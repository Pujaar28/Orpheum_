package com.binargrouptwo.orpheum.model.entities.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binargrouptwo.orpheum.databinding.ItemPlaylistBinding
import com.binargrouptwo.orpheum.databinding.ItemSpotifyRvBinding
import com.binargrouptwo.orpheum.model.entities.browse.FeaturedItems
import com.bumptech.glide.Glide


class FeaturedPlaylistAdapter :
    RecyclerView.Adapter<FeaturedPlaylistAdapter.SpotifyHolder>() {
    lateinit var listFeaturedPlaylist: List<FeaturedItems>

    fun setData(listFeatured: List<FeaturedItems>) {
        this.listFeaturedPlaylist = listFeatured
    }

    class SpotifyHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listFeatured: FeaturedItems) {
            binding.apply {
                tvTitle.text= listFeatured.name
                tvArtist.text = listFeatured.descriptions
                Glide.with(root)
                    .load(listFeatured.images[0].url)
                    .into(ivTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotifyHolder {
        return SpotifyHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SpotifyHolder, position: Int) =
        holder.bindData(listFeaturedPlaylist[position])


    override fun getItemCount(): Int = listFeaturedPlaylist.size
}