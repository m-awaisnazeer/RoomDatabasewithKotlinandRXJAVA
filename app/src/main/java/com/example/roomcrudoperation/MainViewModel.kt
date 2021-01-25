package com.example.roomcrudoperation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomcrudoperation.Database.Contact
import com.example.roomcrudoperation.Database.ContactDatabase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

     var allContacts: MutableLiveData<List<Contact>>? =null
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var contactDatabase: ContactDatabase
    init {
        allContacts = MutableLiveData()
        compositeDisposable = CompositeDisposable()
        contactDatabase = ContactDatabase.getInstance(application)!!

    }

    fun  getContacts(): MutableLiveData<List<Contact>>
    {
        if (allContacts !=null){
            compositeDisposable.add(contactDatabase.contactDao().getAllContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list->
                    Toast.makeText(getApplication(), "Success", Toast.LENGTH_SHORT).show()
                    allContacts!!.value = list

                },{
                    Toast.makeText(getApplication(), ""+it.message, Toast.LENGTH_SHORT).show()
                }))
        }

        return allContacts!!
    }


}