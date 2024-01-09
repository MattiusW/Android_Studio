package com.example.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budget.adapters.ContactsAdapter
import com.example.budget.data.Contact
import com.example.budget.databinding.ActivityMainBinding
import com.example.budget.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val c1 = Contact("John", "Smith", "532452311")
        val c2 = Contact("Jenny", "Smith", "4545411")
        val c3 = Contact("Jim", "Smith", "32412341")
        val contactList = listOf(c1,c2,c3,c1,c2,c3,c1,c2,c3,c1,c2,c3,c1,c2,c3)

        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = ContactsAdapter(contactsList = contactList,
            callListener = {
                Toast.makeText(applicationContext, "Call to number: $it", Toast.LENGTH_SHORT).show()
            },
            smsListener = {
                Toast.makeText(applicationContext, "Send sms to: $it", Toast.LENGTH_SHORT).show()
            })
    }
}