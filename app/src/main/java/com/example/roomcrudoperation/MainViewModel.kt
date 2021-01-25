package com.example.roomcrudoperation

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomcrudoperation.Database.Contact
import com.example.roomcrudoperation.Database.ContactDao
import com.example.roomcrudoperation.Database.ContactDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(application: Application) : AndroidViewModel(application) {

    var allContacts: MutableLiveData<List<Contact>>? = null
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var contactDatabase: ContactDatabase

    init {
        allContacts = MutableLiveData()
        compositeDisposable = CompositeDisposable()
        contactDatabase = ContactDatabase.getInstance(application)!!

    }

    fun getContacts(): MutableLiveData<List<Contact>> {
        if (allContacts != null) {
            compositeDisposable.add(
                contactDatabase.contactDao().getAllContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ list ->
                        allContacts!!.value = list

                    }, {
                        Toast.makeText(getApplication(), "" + it.message, Toast.LENGTH_SHORT).show()
                    })
            )
        }

        return allContacts!!
    }


    fun addContact(contact: Contact) {
        compositeDisposable.add(
            contactDatabase.contactDao().insertContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(getApplication(), "Contact Added", Toast.LENGTH_SHORT).show()
                }, {

                })
        )
    }

    fun deleteContact(contact: Contact) {
        compositeDisposable.add(
            contactDatabase.contactDao().deleteContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(getApplication(), "Contact Deleted", Toast.LENGTH_SHORT).show()
                }, {

                })
        )
    }

    fun updateContact(contact: Contact) {

        UpdateContact(contactDatabase.contactDao()).execute(contact)

//        compositeDisposable.add(
//            contactDatabase.contactDao().updateContact(contact)
//                .observeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Toast.makeText(getApplication(), "Contact Updated", Toast.LENGTH_SHORT).show()
//
//                }, {
//                    Toast.makeText(
//                        getApplication(),
//                        "Contact Updation Failed" + it.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                })
//        )

    }

    private class UpdateContact(contactDao: ContactDao) :
        AsyncTask<Contact?, Void?, Void?>() {
        private val contactDao: ContactDao


        init {
            this.contactDao = contactDao
        }

        override fun doInBackground(vararg params: Contact?): Void? {
            contactDao.updateContact(params[0]!!)
            Log.d("TAG", "doInBackground: updated")
            return null
        }
    }

}