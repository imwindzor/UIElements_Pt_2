
package com.example.uielements_pt_2

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var songsArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsArray = arrayOf("Graves Into Gardens", "Available", "Never Lost", "My Testimony", "Echo", "With You",
            "Here Again", "Worthy", "Perfect Peace", "Done", "Until Grace", "Trenches")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val myList = findViewById<ListView>(R.id.songsQueueListView)
        myList.adapter = adapter

        registerForContextMenu(myList)
        myList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->  }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_queue -> {
                val selectedItemOrder = item!!.order
                val selectedItemTitle = item.title

                val info = item.menuInfo as AdapterContextMenuInfo
                val listPosition = info.position
                if(listPosition == 0) {
                    val intent = intent
                    intent.putExtra("song1", songsArray[listPosition])
                }
                else if(listPosition == 1){
                    val intent = intent
                    intent.putExtra("song2", songsArray[listPosition])
                }
                else if(listPosition == 2){
                    val intent = intent
                    intent.putExtra("song3", songsArray[listPosition])
                }
                else if(listPosition == 3){
                    val intent = intent
                    intent.putExtra("song4", songsArray[listPosition])
                }
                else if(listPosition == 4){
                    val intent = intent
                    intent.putExtra("song5", songsArray[listPosition])
                }
                else if(listPosition == 5){
                    val intent = intent
                    intent.putExtra("song6", songsArray[listPosition])
                }
                else if(listPosition == 6){
                    val intent = intent
                    intent.putExtra("song7", songsArray[listPosition])
                }
                else if(listPosition == 7){
                    val intent = intent
                    intent.putExtra("song8", songsArray[listPosition])
                }
                else if(listPosition == 8){
                    val intent = intent
                    intent.putExtra("song9", songsArray[listPosition])
                }
                else if(listPosition == 9){
                    val intent = intent
                    intent.putExtra("song10", songsArray[listPosition])
                }
                else if(listPosition == 10){
                    val intent = intent
                    intent.putExtra("song11", songsArray[listPosition])
                }
                else if(listPosition == 11){
                    val intent = intent
                    intent.putExtra("song12", songsArray[listPosition])
                }
                else if(listPosition == 12){
                    val intent = intent
                    intent.putExtra("song13", songsArray[listPosition])
                }

                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_to_songs -> {
                true
            }
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queues -> {
                startActivity(Intent(this, QueuedSongsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
