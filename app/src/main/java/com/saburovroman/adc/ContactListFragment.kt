package com.saburovroman.adc

import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class ContactListFragment: Fragment() {
    //на будущее как вариант для хранения контактов с их view-шками
    companion object {
        lateinit var contactMap: MutableMap<View, Contact>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        //пока что вставляем рандомное тестовое значение
        val contact = Contact("Roman Saburov",
            "+79501771105", "+79225159618",
            "roma.saburov.01@mail.ru", "saburovr.01@gmail.ru",
            "описание контакта")
        createContact(view, contact)
        setValuesOnView(view)
        val toolbar = activity?.findViewById<Toolbar>(R.id.contacts)
        toolbar?.title = "Список контактов"
        return view

    }

    private fun createContact(view: View, contact: Contact) {
        contactMap = mutableMapOf(view to contact)
    }

    private fun setValuesOnView(view: View) {
        val contact = contactMap[view]
        val name = view.findViewById<TextView>(R.id.contact_name)
        name.text = contact?.name
        val firstNum = view.findViewById<TextView>(R.id.contact_number)
        firstNum.text = contact?.firstNumber
        val image = view.findViewById<ImageView>(R.id.contact_image)
        contact?.avatar?.let { image.setImageResource(it) }
    }
}