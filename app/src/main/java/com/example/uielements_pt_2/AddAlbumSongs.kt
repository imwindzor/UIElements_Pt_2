package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.AlbumSong

class AddAlbumSongs : AppCompatActivity() {
    lateinit var btnConfirm: Button
    lateinit var etTitle: EditText
    lateinit var etArtist: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album_songs)

        val databaseHelper = DatabaseHandler(this)
        etTitle = findViewById(R.id.songTitle_album)
        etArtist = findViewById(R.id.songArtist_album)
        btnConfirm = findViewById(R.id.btn_confirm_add_song_main_album)

        btnConfirm.setOnClickListener {
            val title = etTitle.text.toString()
            val artist = etArtist.text.toString()

            if(title.isNotEmpty() && artist.isNotEmpty() ) {
                val albumSong = AlbumSong(title = title, artist = artist)
                databaseHelper.albumSongCreate(albumSong)
                Toast.makeText(this,"Song added to album successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Oops. Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }
    fun clearFields(){
        etTitle.text.clear()
        etArtist.text.clear()
    }
}