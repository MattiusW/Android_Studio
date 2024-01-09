package com.example.budget.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.data.Contact
import com.example.budget.databinding.FragmentSecondBinding
import com.example.budget.databinding.ListRowBinding

class ContactsAdapter(private val contactsList: List<Contact>,
                      private val callListener: (String) -> Unit,
                      private val smsListener: (String) -> Unit
): RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(binding: ListRowBinding): RecyclerView.ViewHolder(binding.root) {
        val contactName = binding.contactName
        val contactNumber = binding.contactNumber

        init {
            binding.callBtn.setOnClickListener {
                callListener(contactsList[adapterPosition].number)
            }
            binding.smsBtn.setOnClickListener {
                smsListener(contactsList[adapterPosition].number)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return  contactsList.size
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val fullName = "${contactsList[position].name} ${contactsList[position].surname}"
        holder.contactName.text = fullName
        holder.contactName.text = contactsList[position].name
    }
}