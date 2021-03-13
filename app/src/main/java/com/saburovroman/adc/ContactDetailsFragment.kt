package com.saburovroman.adc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class ContactDetailsFragment(private val viewId: Int): Fragment()  {
    //идея следующая - по ID view-шки, которая сопоставлена с контактом достать из Map-ы
    //нужный контакт и его данным заполнить детальную информацию о контакте
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_details, container, false)
        setValuesOnView(view, viewId)
        val toolbar = activity?.findViewById<Toolbar>(R.id.contacts)
        toolbar?.title = "Детали контакта"
        return view
    }

    private fun setValuesOnView(view: View, id: Int) {
        val key = ContactListFragment.contactMap.keys.find { it.id == id }
        val contact = ContactListFragment.contactMap[key]
        val name = view.findViewById<TextView>(R.id.contact_name)
        name.text = contact?.name
        val firstNum = view.findViewById<TextView>(R.id.contact_number)
        firstNum.text = contact?.firstNumber
        val firstEmail = view.findViewById<TextView>(R.id.first_email)
        firstEmail.text = contact?.firstEmail
        val secondEmail = view.findViewById<TextView>(R.id.second_email)
        secondEmail.text = contact?.secondEmail
        val secondNum = view.findViewById<TextView>(R.id.contact_number2)
        secondNum.text = contact?.secondNumber
        val description = view.findViewById<TextView>(R.id.contact_description)
        description.text = contact?.description
        val image = view.findViewById<ImageView>(R.id.contact_image)
        contact?.avatar?.let { image.setImageResource(it) }
    }
}