package com.binargrouptwo.orpheum.model.entities.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binargrouptwo.orpheum.databinding.ItemSpotifyRvBinding
import com.binargrouptwo.orpheum.model.entities.library.PlaylistItem


class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {
    lateinit var listPlaylist: List<PlaylistItem>

    fun setData(listPlaylist: List<PlaylistItem>) {
        this.listPlaylist = listPlaylist
    }

    class PlaylistHolder(private val binding: ItemSpotifyRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
      fun bindData(listPlaylist: PlaylistItem){
          binding.apply {
              tvNameTitle.text = listPlaylist.name
              tvArtist.text = listPlaylist.description
          }
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        return PlaylistHolder(ItemSpotifyRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listPlaylist.size


    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.bindData(listPlaylist[position])
     }
}