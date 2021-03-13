package com.saburovroman.adc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ContactDetailsFragment(): Fragment()  {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    companion object {
        private const val CONTACT_ID = "CONTACT_ID"

        fun newInstance(id: Int) = ContactDetailsFragment().apply {
            arguments = bundleOf(Companion.CONTACT_ID to id)
        }
    }
}