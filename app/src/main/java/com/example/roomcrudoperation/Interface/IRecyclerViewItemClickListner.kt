package com.example.roomcrudoperation.Interface

import com.example.roomcrudoperation.Database.Contact

interface IRecyclerViewItemClickListner {
    fun onItemClick(isDeleted:Boolean,isEdited:Boolean,position:Int,contact: Contact)
}