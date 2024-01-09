package com.example.budget.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budget.data.Contact
import kotlinx.coroutines.flow.flow

class SecondViewModel: ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts
    val contactFlow = flow {
        for (i in 0..1_000) {
            val c1 = Contact("$i", "$i", "$i")
            kotlinx.coroutines.delay(1000)
            emit(c1)
        }
    }

    fun getContactList(): List<Contact> {
        val c1 = Contact("John", "Smith", "532452311")
        val c2 = Contact("Jenny", "Smith", "4545411")
        val c3 = Contact("Jim", "Smith", "32412341")
        return listOf(c1,c2,c3,c1,c2,c3,c1,c2,c3,c1,c2,c3,c1,c2,c3)
    }

    fun updateContactList() {
        val c1 = Contact("Nowy", "Gracz", "777")
        _contacts.value = listOf(c1)
    }
}