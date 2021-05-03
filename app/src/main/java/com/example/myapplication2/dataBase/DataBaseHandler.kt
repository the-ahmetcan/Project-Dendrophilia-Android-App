package com.example.myapplication2.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHandler(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

            companion object{
                private const val DATABASE_VERSION = 1
                private const val DATABASE_NAME = "PlantDatabase"
                private const val TABLE_CONTACTS = "PlantTable"

                private const val KEY_ID = "_id"
                private const val KEY_SAVED_NAME = "name"
                private const val KEY_SAVED_DESC = "desc"
                private const val KEY_SAVED_IMAGE = "image"
            }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_SAVED_NAME + " TEXT," +
                KEY_SAVED_DESC + " TEXT," +
                KEY_SAVED_IMAGE + " INTEGER" + ")")

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addPlant(dbm: DataBaseModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_SAVED_NAME, dbm.savedName)
        contentValues.put(KEY_SAVED_DESC, dbm.savedDesc)
        contentValues.put(KEY_SAVED_IMAGE, dbm.savedImage)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun viewPlant(): ArrayList<DataBaseModel> {
        val dbmList: ArrayList<DataBaseModel> = ArrayList<DataBaseModel>()

        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var desc: String
        var image: Int

        if(cursor.moveToFirst()) {
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_SAVED_NAME))
                desc = cursor.getString(cursor.getColumnIndex(KEY_SAVED_DESC))
                image = cursor.getInt(cursor.getColumnIndex(KEY_SAVED_IMAGE))

                val dbm = DataBaseModel(id = id, savedName = name, savedDesc = desc, savedImage = image)
                dbmList.add(dbm)
            } while (cursor.moveToNext())
        }
        return dbmList
    }

    fun deletePlant(dbm: DataBaseModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, dbm.id)

        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + dbm.id, null)

        db.close()
        return success
    }
}