package com.saburovroman.adc

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
//colorPrimary - #4fd502
//colorAccent - #302eb5
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val contactList = ContactListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_field, contactList)
                .commit()
        }
    }

    fun onContactClick(view: View) {
        //передается id контакта, чтобы затем из Map-ы заполнить детальную информацию о контакте,
        //когда потребуется работать с многими контактами (как я понял, на данном этапе нам это не нужно,
        //т.к. сейчас нужно оформить лишь верстку и навигацию)
        val fragment = ContactDetailsFragment(view.id)
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_field, fragment)
            .addToBackStack(null)
            .commit()
    }
}