package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Album

class AddAlbum : AppCompatActivity() {
    lateinit var albumTitle: EditText
    lateinit var albumReleaseDate: EditText
    lateinit var confirmBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)
        val databaseHelper = DatabaseHandler(this)

        albumTitle = findViewById(R.id.albumTitle)
        albumReleaseDate = findViewById(R.id.releaseDate)
        confirmBtn = findViewById(R.id.btn_confirm_add)
        confirmBtn.setOnClickListener {

            val Albumtitle = albumTitle.text.toString()
            val releaseDate = albumReleaseDate.text.toString()

            if(Albumtitle.isNotEmpty() && releaseDate.isNotEmpty()) {
                val album = Album(title = Albumtitle, releaseDate = releaseDate)
                databaseHelper.albumCreate(album)
                Toast.makeText(this,"Album added successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Oops, something went wrong. Please try again", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }

    fun clearFields(){
        albumTitle.text.clear()
        albumReleaseDate.text.clear()
    }
}
