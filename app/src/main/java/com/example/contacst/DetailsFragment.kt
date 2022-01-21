package com.example.contacst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun newInstance(contact:Contact):DetailsFragment{
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("contactList",contact)
        detailsFragment.arguments = bundle
        return detailsFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact  = arguments?.getParcelable<Contact>("contactList")

        val textView = view.findViewById<TextView>(R.id.tv_contact)

        textView.text = contact.toString()
    }

}