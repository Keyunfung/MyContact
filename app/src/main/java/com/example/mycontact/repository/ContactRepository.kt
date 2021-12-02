package com.example.mycontact.repository

import androidx.lifecycle.LiveData
import com.example.mycontact.dao.ContactDao
import com.example.mycontact.model.Contact

//Serve as a middle man between application and the data source (local or create or remote database)
class ContactRepository (private val contactDao: ContactDao) {
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContact()

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact) {
        contactDao.delete(contact)
    }
}