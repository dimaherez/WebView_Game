package com.example.testsmartapps.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private var instance: SQLiteHelper? = null
            fun getInstance(context: Context): SQLiteHelper {
                if (instance == null) {
                    instance = SQLiteHelper(context)
                }

                return instance!!
            }

            //Constants for db name and version
            private const val DATABASE_NAME = "response.db"
            private const val DATABASE_VERSION = 1

            //Constants for identifying table and columns
            const val TABLE_RESPONSES = "last_response"
            private const val ID = "id"
            private const val RESPONSE_VALUE = "response_value"

            //SQL to create table
            private const val TABLE_CREATE = "CREATE TABLE " + TABLE_RESPONSES + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RESPONSE_VALUE + " BOOLEAN" +
                    ")"
        }

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(TABLE_CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_RESPONSES")
            onCreate(db)
        }

        fun insertValue(responseValue: Boolean): Long {
            if (checkIsDataAlreadyInDB()) {
                return 0
            }

            val db = this.writableDatabase

            val contentValues = ContentValues()

            contentValues.put(RESPONSE_VALUE, responseValue)

            val result = db.insert(TABLE_RESPONSES, null, contentValues)
            db.close()
            return result
        }

        private fun checkIsDataAlreadyInDB(): Boolean {
            val db = this.readableDatabase
            val query = "Select * from $TABLE_RESPONSES"
            val cursor = db.rawQuery(query, null)
            if (cursor.count <= 0) {
                cursor.close()
                return false
            }
            cursor.close()
            db.close()
            return true
        }

        fun getValue(): Boolean? {
            val db = this.readableDatabase
            val query = "Select * from $TABLE_RESPONSES"
            val cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                return cursor.getInt(0) > 0
            }

            return null
        }
}