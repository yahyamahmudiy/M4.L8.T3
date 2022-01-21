package com.example.contacst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: ArrayList<Contact>, val itemClickListener:ItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = contacts[position]

        (holder as ContactViewHolder).apply {
            tv_contact_name.text = contact.name
            tv_contact_number.text = contact.phoneNumber
            linearLayout.setOnClickListener {
                itemClickListener.itemClick(contact)
            }
        }
    }

    override fun getItemCount(): Int = contacts.size

    class ItemClickListener(val itemClick:(contact:Contact)->Unit)

    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tv_contact_name: TextView = view.findViewById(R.id.tv_contact_name)
        val tv_contact_number: TextView = view.findViewById(R.id.tv_contact_number)
        val linearLayout: LinearLayout = view.findViewById(R.id.ll_item_layout)
    }
}