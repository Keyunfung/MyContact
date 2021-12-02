package com.example.mycontact.ui.insert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mycontact.database.ContactDatabase
import com.example.mycontact.model.Contact
import com.example.mycontact.repository.ContactRepository
import kotlinx.coroutines.launch

class InsertViewModel (application: Application): AndroidViewModel(application) {
    var contactList: LiveData<List<Contact>>
    private val repository: ContactRepository

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        contactList = repository.allContacts
    }

    fun insertContact(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }
}