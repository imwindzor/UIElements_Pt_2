package com.example.uielements_pt_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView

class AlbumsActivity : AppCompatActivity() {


    private var arrayList = ArrayList<AlbumItem>()
    var images = intArrayOf(R.drawable.hallelujah, R.drawable.graves_gardens, R.drawable.tauren)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        for (i in images.indices) {
            arrayList.add(AlbumItem(images[i]))
        }

        var albumAdapter = AlbumAdapter(arrayList, this)

        var gridView = findViewById<GridView>(R.id.grid_view)
        gridView.adapter = albumAdapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("data", arrayList[position])
            startActivity(intent)
        }
    }

    class AlbumAdapter(
        var albumItem: ArrayList<AlbumItem>,
        var context: Context

    ) : BaseAdapter() {

        var layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.grid_item_list, parent, false)
            }

            var imageView = view?.findViewById<ImageView>(R.id.icons)

            imageView?.setImageResource(albumItem[position].icons!!)

            return view!!
        }

        override fun getItem(position: Int): Any {
            return albumItem[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return albumItem.size
        }

    }
}
