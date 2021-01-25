package com.example.roomcrudoperation.Database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ContactDao {

    @Insert
    fun insertContact(contact: Contact): Completable

    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flowable<List<Contact>>

    @Delete
    fun deleteContact(contact: Contact): Completable

    @Update
    fun updateContact(contact: Contact)

}