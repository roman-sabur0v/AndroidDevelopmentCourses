package com.saburovroman.adc

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.ref.WeakReference

class ContactService : Service() {

    private val binder = ContactBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    fun getContactList(callback: ContactListReceivable<Contact>) {
        val weakReferenceCallback = WeakReference(callback)
        Thread {
            weakReferenceCallback.get()?.getList(DataForService.contactList)
        }.start()
    }

    fun getContactDetails(callback: ContactDetailsReceivable, id: Int) {
        val weakReferenceCallback = WeakReference(callback)
        Thread {
            Thread.sleep(2000)
            weakReferenceCallback.get()?.getContactDetails(DataForService.contactList[id])
        }.start()
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }

    interface IService {
        fun getService(): ContactService?
    }
}