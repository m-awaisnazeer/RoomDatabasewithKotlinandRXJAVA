package com.example.roomcrudoperation.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contact::class],version = 1)
abstract class ContactDatabase:RoomDatabase() {

    abstract fun contactDao():ContactDao


    companion object{
        @Volatile private var instance:ContactDatabase?=null

        @Synchronized
        fun getInstance(context: Context): ContactDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ContactDatabase::class.java, "contact_database"
                ).build()
            }
            return instance
        }
    }


}