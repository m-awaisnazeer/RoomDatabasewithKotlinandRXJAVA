package com.example.roomcrudoperation.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int?=null,
    @ColumnInfo(name = "name") var name:String? = null,
    @ColumnInfo(name="phone") var phone:String? = null,
    @ColumnInfo(name="email") var email:String? = null
) {
}
