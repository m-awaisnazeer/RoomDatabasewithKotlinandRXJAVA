package com.example.roomcrudoperation.Database

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface ContactDao {

    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getAllContacts():Flowable<List<Contact>>

    @Delete
    fun deleteContact(contact: Contact)



}