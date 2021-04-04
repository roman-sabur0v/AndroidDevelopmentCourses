package com.saburovroman.adc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder

class MainActivity : AppCompatActivity(), ContactListFragment.OnContactClickedListener, ContactService.IService {

    private var contactService: ContactService? = null
    private var bound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as ContactService.ContactBinder
            contactService = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState  == null) {
            initContactService()
            initContactListFragment()
        }
    }

    override fun onDestroy() {
        if(bound) {
            unbindService(connection)
            bound = false
        }
        contactService = null
        super.onDestroy()
    }

    override fun onContactClicked(id: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContactDetailsFragment.newInstance(id))
                .addToBackStack(null)
                .commit()
    }

    private fun initContactService() {
        val intent = Intent(this, ContactService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun initContactListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ContactListFragment())
            .commit()
    }

    override fun getService() = contactService
}