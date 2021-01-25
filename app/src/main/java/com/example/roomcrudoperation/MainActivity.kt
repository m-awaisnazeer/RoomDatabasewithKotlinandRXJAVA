package com.example.roomcrudoperation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrudoperation.Database.Contact
import com.example.roomcrudoperation.Interface.IRecyclerViewItemClickListner
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), IRecyclerViewItemClickListner {

    lateinit var add_to_contacts:FloatingActionButton
    lateinit var fm: FragmentManager
    lateinit var model:MainViewModel
    lateinit var adapter: RecyclerViewAdapter
    lateinit var contacts_listRV:RecyclerView
    lateinit var addContactFragmentDialog: AddContactFragmentDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        add_to_contacts = findViewById(R.id.add_to_contacts)


        fm = supportFragmentManager


        contacts_listRV = findViewById(R.id.contacts_listRV)
        contacts_listRV.setHasFixedSize(true)
        contacts_listRV.layoutManager = LinearLayoutManager(this)
        model = ViewModelProvider(this).get(MainViewModel::class.java)
        model.getContacts().observe(this, Observer {
            adapter = RecyclerViewAdapter(it,this)
            contacts_listRV.adapter = adapter
        })



        add_to_contacts.setOnClickListener {
            addContactFragmentDialog =
                AddContactFragmentDialog.newInstance("new request")!!
            addContactFragmentDialog!!.show(fm, "add_contact_dialog")
            addContactFragmentDialog!!.isCancelable = false
        }
    }

    override fun onItemClick(
        isDeleted: Boolean,
        isEdited: Boolean,
        position: Int,
        contact: Contact
    ) {

        if (isDeleted){
            model.deleteContact(contact)
            adapter.notifyItemRemoved(position)
        }
        if (isEdited ){
            Constants.selectedContact = contact
            addContactFragmentDialog =
                AddContactFragmentDialog.newInstance("new request")!!
            addContactFragmentDialog!!.show(fm, "add_contact_dialog")
            addContactFragmentDialog!!.isCancelable = false

            model.getContacts().observe(this, Observer {
                adapter = RecyclerViewAdapter(it,this)
                contacts_listRV.adapter = adapter
            })

        }
    }


}