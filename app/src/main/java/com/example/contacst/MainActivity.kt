package com.example.contacst

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var contactList:ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactList = ArrayList()

        checkPermission()

        getContacts()

        val contactListFragment = ContactListFragment().newInstance(contactList)

        addFragment(contactListFragment)

        contactListFragment.setClickListener(ClickListener {
            val detailsFragment = DetailsFragment().newInstance(it)
            addFragment(detailsFragment)
        })

    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment,"fragmentContact").addToBackStack("frag").commit()
    }

    private fun getContacts() {

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)

        cursor!!.moveToFirst()

        while (cursor.moveToNext()) {
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    ?: "Unknown"
            val phone =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contact = Contact(name = name, phoneNumber = phone)
            contactList.add(contact)
        }

        Log.d("Contacts", "getContacts: $contactList")

        cursor.close()

    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                909
            )
        }
    }
}
