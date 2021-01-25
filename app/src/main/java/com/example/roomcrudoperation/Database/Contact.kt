package com.example.roomcrudoperation.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @ColumnInfo(name = "name") var name:String? = null,
    @ColumnInfo(name="phone") var phone:String? = null,
    @ColumnInfo(name="email") var email:String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
