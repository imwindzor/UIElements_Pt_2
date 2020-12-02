package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Album

class EditAlbum : AppCompatActivity() {
    lateinit var albumTitle: EditText
    lateinit var albumRelease: EditText
    lateinit var confirmBtn: Button
    lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)
        val album_id = intent.getIntExtra("album_id", 0)
        val databaseHelper = DatabaseHandler(this)

        album = databaseHelper.albumReadOne(album_id)
        albumTitle = findViewById(R.id.edit_Title)
        albumRelease = findViewById(R.id.edit_releaseDate)
        confirmBtn = findViewById(R.id.edit_btn_confirm_add_album_main)
        albumTitle.setText(album.title)
        albumRelease.setText(album.releaseDate)

        confirmBtn.setOnClickListener {
            val title = albumTitle.text.toString()
            val releaseDate = albumRelease.text.toString()

            if(title.isNotEmpty() && releaseDate.isNotEmpty()) {
                val albumUpdate = Album(id = album.id, title = title, releaseDate = releaseDate)
                databaseHelper.albumUpdate(albumUpdate)
                Toast.makeText(this,"Edited successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }
}