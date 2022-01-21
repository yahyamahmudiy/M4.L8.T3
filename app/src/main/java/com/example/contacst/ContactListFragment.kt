package com.example.contacst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactListFragment : Fragment() {

    private lateinit var clickListener: ClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun newInstance(contactList:ArrayList<Contact>):ContactListFragment{
        val contactListFragment = ContactListFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("contactList",contactList)
        contactListFragment.arguments = bundle
        return contactListFragment
    }

    fun setClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactList = arguments?.getParcelableArrayList<Contact>("contactList")

        val rvContacts = view.findViewById<RecyclerView>(R.id.rv_contacts)

        val contactAdapter = ContactAdapter(contactList as ArrayList<Contact>,ContactAdapter.ItemClickListener{
            clickListener.click(it)
        })

        rvContacts.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}