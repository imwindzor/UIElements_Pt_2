package com.example.uielements_pt_2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.uielements_pt_2.models.Album
import com.example.uielements_pt_2.models.AlbumSong
import com.example.uielements_pt_2.models.Song

class DatabaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songs_database"

        //song
        private val TABLE_NAME = "songs"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_ARTIST = "artist"
        private val COL_ALBUM = "album"

        //album
        private val TABLE_ALBUM = "albums"
        private val ID_ALBUM = "id"
        private val ALBUM_TITLE = "title"
        private val ALBUM_RELEASE_DATE = "releaseDate"

        //album song
        private val TABLE_ALBUM_SONG = "albumSongs"
        private val ID_ALBUM_SONG = "id"
        private val ALBUM_SONG_TITLE = "title"
        private val ALBUM_SONG_ARTIST = "artist"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //song
        val query =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_TITLE TEXT, $COL_ARTIST TEXT, $COL_ALBUM TEXT)"
        db?.execSQL(query)

        //album
        val query2 =
            "CREATE TABLE $TABLE_ALBUM ($ID_ALBUM INTEGER PRIMARY KEY, $ALBUM_TITLE TEXT, $ALBUM_RELEASE_DATE TEXT)"
        db?.execSQL(query2)

        //album song
        val query3 =
            "CREATE TABLE $TABLE_ALBUM_SONG ($ID_ALBUM_SONG INTEGER PRIMARY KEY, $ALBUM_SONG_TITLE TEXT, $ALBUM_SONG_ARTIST TEXT)"
        db?.execSQL(query3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //song
        db!!.execSQL("DROP TABLE IF EXIST" + TABLE_NAME)

        //album
        db.execSQL("DROP TABLE IF EXIST" + TABLE_ALBUM)

        //album song
        db.execSQL("DROP TABLE IF EXIST" + TABLE_ALBUM_SONG)

        onCreate(db)
    }

    //song
    fun create(song: Song): Boolean {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, song.title)
        contentValues.put(COL_ARTIST, song.artist)
        contentValues.put(COL_ALBUM, song.album)

        val result = database.insert(TABLE_NAME, null, contentValues)

        if (result == (0).toLong()) {
            return false
        }
        return true
    }

    fun update(song: Song): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, song.title)
        contentValues.put(COL_ARTIST, song.artist)
        contentValues.put(COL_ALBUM, song.album)

        val result = database.update(TABLE_NAME, contentValues, "id=" + song.id, null)

        if (result == 0) {
            return false
        }
        return true
    }

    fun delete(song: Song): Boolean {
        val database = this.writableDatabase
        val result = database.delete(TABLE_NAME, "id=${song.id}", null)
        if (result == 0) {
            return false
        }
        return true
    }

    fun read(): MutableList<Song> {
        val songsList: MutableList<Song> = ArrayList<Song>()
        val query = "SELECT * FROM $TABLE_NAME"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return songsList
        }

        var id: Int
        var title: String
        var artists: String
        var album: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                artists = cursor.getString(cursor.getColumnIndex(COL_ARTIST))
                album = cursor.getString(cursor.getColumnIndex(COL_ALBUM))
                val song = Song(id, title, artists, album)
                songsList.add(song)
            } while (cursor.moveToNext())
        }
        return songsList
    }

    fun readOne(song_id: Int): Song {
        var song: Song = Song(0, "", "", "")
        val query = "SELECT * FROM $TABLE_NAME WHERE id = $song_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return song
        }

        var id: Int
        var title: String
        var artists: String
        var album: String
        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(COL_ID))
            title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
            artists = cursor.getString(cursor.getColumnIndex(COL_ARTIST))
            album = cursor.getString(cursor.getColumnIndex(COL_ALBUM))
            song = Song(id, title, artists, album)
        }
        return song
    }

    //album
    fun albumCreate(album: Album): Boolean {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ALBUM_TITLE, album.title)
        contentValues.put(ALBUM_RELEASE_DATE, album.releaseDate)

        val result = database.insert(TABLE_ALBUM, null, contentValues)

        if (result == (0).toLong()) {
            return false
        }
        return true
    }

    fun albumUpdate(album: Album): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ALBUM_TITLE, album.title)
        contentValues.put(ALBUM_RELEASE_DATE, album.releaseDate)

        val result = database.update(TABLE_ALBUM, contentValues, "id=" + album.id, null)

        if (result == 0) {
            return false
        }
        return true
    }

    fun albumDelete(album: Album): Boolean {
        val database = this.writableDatabase
        val result = database.delete(TABLE_ALBUM, "id=${album.id}", null)
        if (result == 0) {
            return false
        }
        return true
    }

    fun albumRead(): MutableList<Album> {
        val albumList: MutableList<Album> = ArrayList<Album>()
        val query = "SELECT * FROM " + TABLE_ALBUM
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return albumList
        }

        var id: Int
        var title: String
        var releaseDate: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM))
                title = cursor.getString(cursor.getColumnIndex(ALBUM_TITLE))
                releaseDate = cursor.getString(cursor.getColumnIndex(ALBUM_RELEASE_DATE))

                val album = Album(id, title, releaseDate)
                albumList.add(album)
            } while (cursor.moveToNext())
        }
        return albumList
    }

    fun albumReadOne(album_id: Int): Album {
        var albumOne: Album = Album(0, "", "")
        val query = "SELECT * FROM $TABLE_ALBUM WHERE id = $album_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return albumOne
        }

        var id: Int
        var title: String
        var releaseDate: String

        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM))
            title = cursor.getString(cursor.getColumnIndex(ALBUM_TITLE))
            releaseDate = cursor.getString(cursor.getColumnIndex(ALBUM_RELEASE_DATE))

            albumOne = Album(id, title, releaseDate)
        }
        return albumOne
    }

    //album song
    fun albumSongCreate(albumSong: AlbumSong): Boolean {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ALBUM_SONG_TITLE, albumSong.title)
        contentValues.put(ALBUM_SONG_ARTIST, albumSong.artist)

        val result = database.insert(TABLE_ALBUM_SONG, null, contentValues)

        if (result == (0).toLong()) {
            return false
        }
        return true
    }

    fun albumSongUpdate(albumSong: AlbumSong): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ALBUM_SONG_TITLE, albumSong.title)
        contentValues.put(ALBUM_SONG_ARTIST, albumSong.artist)

        val result = database.update(TABLE_ALBUM_SONG, contentValues, "id=" + albumSong.id, null)

        if (result == 0) {
            return false
        }
        return true
    }

    fun albumSongDelete(albumSong: AlbumSong): Boolean {
        val database = this.writableDatabase
        val result = database.delete(TABLE_ALBUM_SONG, "id=${albumSong.id}", null)
        if (result == 0) {
            return false
        }
        return true
    }

    fun albumSongRead(): MutableList<AlbumSong> {
        val albumList: MutableList<AlbumSong> = ArrayList<AlbumSong>()
        val query = "SELECT * FROM " + TABLE_ALBUM_SONG
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return albumList
        }

        var id: Int
        var title: String
        var artist: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM_SONG))
                title = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_TITLE))
                artist = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_ARTIST))

                val album = AlbumSong(id, title, artist)
                albumList.add(album)
            } while (cursor.moveToNext())
        }
        return albumList
    }

    fun albumSongReadOne(album_id: Int): AlbumSong {
        var albumOne: AlbumSong = AlbumSong(0, "", "")
        val query = "SELECT * FROM $TABLE_ALBUM_SONG WHERE id = $album_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLiteException) {
            return albumOne
        }

        var id: Int
        var title: String
        var artist: String

        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM_SONG))
            title = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_TITLE))
            artist = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_ARTIST))

            albumOne = AlbumSong(id, title, artist)
        }
        return albumOne
    }
}