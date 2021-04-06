package com.saburovroman.adc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class ContactListFragment: Fragment() {

    private var onContactClicked: OnContactClickedListener? = null
    private var service: ContactService.IService? = null

    private var callback = object : ContactListReceivable<Contact> {
        override fun getList(list: List<Contact>) {
            requireView().post {
                val contactName = requireView().findViewById<TextView>(R.id.contact_name)
                val contactNum = requireView().findViewById<TextView>(R.id.contact_number)
                val contactAvatar = requireView().findViewById<ImageView>(R.id.contact_image)

                contactAvatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), list[0].avatar))
                contactName.text = list[0].fullName
                contactNum.text = list[0].firstNum
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnContactClickedListener)
            onContactClicked = context
        if (context is ContactService.IService)
            service = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ConstraintLayout>(R.id.contact)
            .setOnClickListener { onContactClicked?.onContactClicked(R.id.contact) }
        service?.getService()?.getContactList(callback)
    }

    override fun onDestroy() {
        service = null
        onContactClicked = null
        super.onDestroy()
    }

    interface OnContactClickedListener {
        fun onContactClicked(id : Int)
    }
}