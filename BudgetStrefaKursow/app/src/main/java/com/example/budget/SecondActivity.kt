package com.example.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.adapters.ContactsAdapter
import com.example.budget.data.Contact
import com.example.budget.databinding.ActivityMainBinding
import com.example.budget.databinding.ActivitySecondBinding
import com.example.budget.viewmodels.SecondViewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val vm by viewModels<SecondViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        vm.contacts.observe(this) { contactList ->
            binding.recyclerView.adapter = ContactsAdapter(contactsList = contactList,
                callListener = {
                    Toast.makeText(applicationContext, "Call to number: $it", Toast.LENGTH_SHORT).show()
                },
                smsListener = {
                    Toast.makeText(applicationContext, "Send sms to: $it", Toast.LENGTH_SHORT).show()
                })
        }

        vm.updateContactList()


    }
}