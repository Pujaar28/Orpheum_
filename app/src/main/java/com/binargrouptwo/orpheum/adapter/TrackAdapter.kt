package com.binargrouptwo.orpheum.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binargrouptwo.orpheum.databinding.ItemSpotifyRvBinding
import com.binargrouptwo.orpheum.model.entities.profile.tracks.Item
import com.binargrouptwo.orpheum.ui.playmusic.PlayMusic
import com.bumptech.glide.Glide

class TrackAdapter: RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    lateinit var listTrack: List<Item>

    fun setData(listTracks: List<Item>) {
        this.listTrack = listTracks
    }
    class ViewHolder(private val binding: ItemSpotifyRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(listTrack: Item) {
            binding.apply {
                tvNameTitle.text = listTrack.track.name
                tvArtist.text = listTrack.track.artists[0].name
                Glide.with(binding.root)
                    .load(listTrack.track.album.images[0].url)
                    .into(binding.ivTitle)

                root.setOnClickListener {
                    val intent = Intent(root.context, PlayMusic::class.java)
                    intent.putExtra(PlayMusic.EXTRA_TITLE, listTrack.track.name)
                    intent.putExtra(PlayMusic.EXTRA_ARTIST, listTrack.track.artists[0].name)
                    intent.putExtra(PlayMusic.EXTRA_IMG, listTrack.track.album.images[0].url)
                    intent.putExtra(PlayMusic.EXTRA_MUSIC, listTrack.track.previewUrl)
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSpotifyRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listTrack[position])
    }

    override fun getItemCount(): Int {
        return listTrack.size
    }
}