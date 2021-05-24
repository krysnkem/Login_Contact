package com.example.login_contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_contact.databinding.ContactInfoLayoutBinding


class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    val contacts = mutableListOf<ContactModel>()

    fun setUpItems(categoryContacts : List<ContactModel>){

        contacts.addAll(categoryContacts)
        notifyDataSetChanged()
    }
    fun setUpContact(contact: ContactModel){
        contacts.add(contact)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ContactInfoLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem (contact: ContactModel){
            binding.contactNameTextview.text = contact.name

            binding.contactNumberTextview.text = contact.number
        }

        fun setColor(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactInfoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}