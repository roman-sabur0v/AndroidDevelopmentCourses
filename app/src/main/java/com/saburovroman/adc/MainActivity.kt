package com.saburovroman.adc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ContactListFragment.OnContactClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContactListFragment())
                .commit()
        }
    }

    override fun onContactClicked(id: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContactDetailsFragment.newInstance(id))
                .addToBackStack(null)
                .commit()
    }
}