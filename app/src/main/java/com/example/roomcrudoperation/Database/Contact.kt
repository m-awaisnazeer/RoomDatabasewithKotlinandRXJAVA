package com.example.roomcrudoperation.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name="phone") var phone:String,
    @ColumnInfo(name="email") var email:String
) {
}