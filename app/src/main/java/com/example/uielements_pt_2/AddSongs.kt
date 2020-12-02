package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Song

class AddSongs : AppCompatActivity() {
    lateinit var confirmBtn: Button
    lateinit var etTitle: EditText
    lateinit var etArtist: EditText
    lateinit var etAlbum:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)

        val databaseHelper = DatabaseHandler(this)

        etTitle = findViewById(R.id.songTitle)
        etArtist = findViewById(R.id.songArtist)
        etAlbum = findViewById(R.id.songAlbum)
        confirmBtn = findViewById(R.id.btn_confirm_add_song_main)
        confirmBtn.setOnClickListener {
            val title = etTitle.text.toString()
            val artist = etArtist.text.toString()
            val album = etAlbum.text.toString()

            if(title.isNotEmpty() && artist.isNotEmpty() && album.isNotEmpty()) {
                val song = Song(title = title, artist = artist, album = album)
                databaseHelper.create(song)
                Toast.makeText(this, "Song added successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Please fill up the credentials", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun clearFields(){
        etTitle.text.clear()
        etArtist.text.clear()
        etAlbum.text.clear()
    }
}