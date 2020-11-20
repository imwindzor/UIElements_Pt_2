package com.example.uielements_pt_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var albumItems: AlbumItem = intent.getSerializableExtra("data") as AlbumItem
        var viewImage = findViewById<ImageView>(R.id.icon_details)
        var viewText = findViewById<TextView>(R.id.icon_name)

        if(albumItems.icons == R.drawable.hallelujah) {
            viewImage.setImageResource(albumItems.icons!!)

            val songsQueueArray = arrayOf("Echo", "With You", "Here Again", "Worthy")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
        else if(albumItems.icons == R.drawable.tauren){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Citizen of Heaven"

            val songsQueueArray = arrayOf("Perfect Peace", "Done", "Until Grace", "Trenches")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
        else if(albumItems.icons == R.drawable.graves_gardens){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Graves Into Gardens"

            val songsQueueArray = arrayOf("Graves Into Gardens", "Available", "Never Lost", "My Testimony")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
    }
}