package com.saburovroman.adc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class ContactListFragment: Fragment() {
    private var onContactClicked: OnContactClickedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnContactClickedListener)
            onContactClicked = context
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
    }

    override fun onDestroy() {
        onContactClicked = null
        super.onDestroy()
    }

    interface OnContactClickedListener {
        fun onContactClicked(id : Int)
    }
}