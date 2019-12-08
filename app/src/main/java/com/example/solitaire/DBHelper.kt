package com.example.solitaire

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

//helps activities interact with SQLite DB
class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    //creates db on creation of class
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    //on upgrade, recreates database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    //on downgrade, recreates DB
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    //Inserts a game's data into the database
    @Throws(SQLiteConstraintException::class)
    fun insertGame(game: ScoreModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.ScoreEntry.COLUMN_GAME_ID, game.gameID)
        values.put(DBContract.ScoreEntry.COLUMN_SCORE, game.gameScore)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.ScoreEntry.TABLE_NAME, null, values)
        Log.d("dbfunc1", "Added to database")

        return true
    }

    //finds the lowest number of moves
    fun readBestScore():ArrayList<ScoreModel> {
        //create cursor
        val games = ArrayList<ScoreModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.ScoreEntry.TABLE_NAME, null)
        }
        catch (e: SQLiteException) {

            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        //check DB values using cursor
        var gameID: String
        var score: Int
        var minScore = 5000
        var minID: String = ""
        if(cursor!!.moveToFirst() && cursor != null) {
            while (cursor.isAfterLast == false) {
                gameID =
                    cursor.getString(cursor.getColumnIndex(DBContract.ScoreEntry.COLUMN_GAME_ID))
                score = cursor.getInt(cursor.getColumnIndex(DBContract.ScoreEntry.COLUMN_SCORE))
                if (score < minScore) {
                    minScore = score
                    minID = gameID
                }
                cursor.moveToNext()
            }

            games.add(ScoreModel(minID, minScore))
        }
        return games //send data back to activity
    }

    //finds the total number of games in the database
    fun showNumGames():Int {
        //cursor and db again
        val games = ArrayList<ScoreModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.ScoreEntry.TABLE_NAME, null)
        }
        catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return 0
        }

        //count each game in DB
        var gameID: String
        var score: Int
        if(cursor!!.moveToFirst()) {
            while(cursor.isAfterLast == false) {
                gameID = cursor.getString(cursor.getColumnIndex(DBContract.ScoreEntry.COLUMN_GAME_ID))
                score = cursor.getInt(cursor.getColumnIndex(DBContract.ScoreEntry.COLUMN_SCORE))
                games.add(ScoreModel(gameID, score))
                cursor.moveToNext()
            }
        }
        return games.size
    }

    //contains SQL for use in functions
    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DBContract.ScoreEntry.TABLE_NAME + " (" +
                    DBContract.ScoreEntry.COLUMN_GAME_ID + " TEXT PRIMARY KEY," +
                    DBContract.ScoreEntry.COLUMN_SCORE + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.ScoreEntry.TABLE_NAME
    }
}

