package com.example.roomcrudoperation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrudoperation.Database.Contact

class RecyclerViewAdapter(var allcontacts: List<Contact>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var phone: TextView
        lateinit var email: TextView

        init {
            name = itemView.findViewById(R.id.name)
            phone = itemView.findViewById(R.id.phone)
            email = itemView.findViewById(R.id.email)
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

    }
}