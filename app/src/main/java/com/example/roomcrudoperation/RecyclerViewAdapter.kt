package com.example.roomcrudoperation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrudoperation.Database.Contact
import com.example.roomcrudoperation.Interface.IRecyclerViewItemClickListner

class RecyclerViewAdapter(var allcontacts: List<Contact>,var iRecyclerViewItemClickListner: IRecyclerViewItemClickListner) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var phone: TextView
        lateinit var email: TextView
        lateinit var edit_contact:ImageView
        lateinit var delete_contact:ImageView
        lateinit var id:TextView

        init {
            name = itemView.findViewById(R.id.name)
            phone = itemView.findViewById(R.id.phone)
            email = itemView.findViewById(R.id.email)
            edit_contact = itemView.findViewById(R.id.edit_contact)
            delete_contact = itemView.findViewById(R.id.delete_contact)
            id = itemView.findViewById(R.id.id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return allcontacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact: Contact = allcontacts.get(position)


        holder.name.append(contact.name)
        holder.email.append(contact.email)
        holder.phone.append(contact.phone)
        holder.id.append(contact.id.toString())

        holder.delete_contact.setOnClickListener {
            iRecyclerViewItemClickListner.onItemClick(true,false,position,contact)
        }

        holder.edit_contact.setOnClickListener {
            iRecyclerViewItemClickListner.onItemClick(false,true,position,contact)
        }

    }
}