package com.example.mycontact.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity = Table (OOP = Class)
@Entity(tableName = "contact")
data class Contact (var name: String = "", @PrimaryKey var phone: String = "") {

    override fun toString(): String {
        return "$name : $phone"
    }
}