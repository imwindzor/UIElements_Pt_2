package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView

class QueuedSongsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val songsArray = arrayOf("Graves Into Gardens", "Available", "Never Lost")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val myList = findViewById<ListView>(R.id.queue_songs)
        myList.adapter = adapter


    }
}