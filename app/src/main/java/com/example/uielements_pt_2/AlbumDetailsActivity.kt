package com.example.uielements_pt_2

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.uielements_pt_2.models.Album
import com.example.uielements_pt_2.models.AlbumSong

class AlbumDetailsActivity : AppCompatActivity() {
    lateinit var album: Album
    lateinit var albumTitle: TextView
    lateinit var songsAlbumTableHandler: DatabaseHandler
    lateinit var albumSongItem: MutableList<AlbumSong>
    lateinit var adapter: ArrayAdapter<AlbumSong>
    lateinit var albumSongListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        albumSongListView = findViewById<ListView>(R.id.album_songs)
        val album_id = intent.getIntExtra("album_id", 0)
        songsAlbumTableHandler = DatabaseHandler(this)
        album = songsAlbumTableHandler.albumReadOne(album_id)
        albumTitle = findViewById(R.id.icon_name)
        albumTitle.text = album.title
        albumSongItem = songsAlbumTableHandler.albumSongRead()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumSongItem)
        albumSongListView.adapter = adapter
        registerForContextMenu(albumSongListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.album_details_remove_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position

        return when (item.itemId) {
            R.id.go_to_remove_album_song -> {
                val albumSong = albumSongItem[listPosition]
                if(songsAlbumTableHandler.albumSongDelete(albumSong)){
                    albumSongItem.removeAt(listPosition)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this,"Song deleted successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Song deletion failed", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_a_song_album -> {
                startActivity(Intent(this, AddAlbumSongs::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}