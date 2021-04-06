package com.saburovroman.adc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ContactDetailsFragment: Fragment()  {

    private var service: ContactService.IService? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactService.IService)
            service = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service?.getService()?.getContactDetails(callback, 0)
    }

    private var callback = object : ContactDetailsReceivable {
        override fun getContactDetails(contact: Contact) {
            requireView().post {
                val contactName = requireView().findViewById<TextView>(R.id.contact_name)
                val contactFirstNum = requireView().findViewById<TextView>(R.id.contact_details_number)
                val contactSecondNum = requireView().findViewById<TextView>(R.id.contact_details_second_number)
                val contactEmail = requireView().findViewById<TextView>(R.id.contact_details_email)
                val contactSecondEmail = requireView().findViewById<TextView>(R.id.contact_details_second_email)
                val contactDescription = requireView().findViewById<TextView>(R.id.contact_description)
                val contactAvatar = requireView().findViewById<ImageView>(R.id.contact_details_image)

                contactAvatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), contact.avatar))
                contactDescription.text = contact.description
                contactSecondEmail.text = contact.secondEmail
                contactEmail.text = contact.firstEmail
                contactSecondNum.text = contact.secondNum
                contactFirstNum.text = contact.firstNum
                contactName.text = contact.fullName
            }
        }
    }

    override fun onDetach() {
        service = null
        super.onDetach()
    }

    companion object {
        private const val CONTACT_ID = "CONTACT_ID"

        fun newInstance(id: Int) = ContactDetailsFragment().apply {
            arguments = bundleOf(Companion.CONTACT_ID to id)
        }
    }
}