package com.webage.lab08contentproviders

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.webage.lab08contentproviders.data.Contact

class ContactViewModel : ViewModel() {

    var contactListState :MutableState<List<Contact>> = mutableStateOf(emptyList<Contact>())

    fun loadContacts(context: Context) {

        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )

        val cursor = context.getContentResolver().query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {

                val id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data._ID))

                val displayName =
                    cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME))

                val hasPhoneNumber =
                    cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                val phoneSelection =
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID.toString() + " = ?"
                val phoneSelectionArgs = arrayOf("${id}")

                if (hasPhoneNumber == "1") {
                    val phoneNumberCursor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        phoneSelection,
                        phoneSelectionArgs,
                        null
                    );
                    phoneNumberCursor?.use {
                        it.moveToNext()

                        val phoneNumber = phoneNumberCursor.getString(
                            phoneNumberCursor.getColumnIndexOrThrow(ContactsContract.Data.DATA1)
                        )

                        (contactListState.value + listOf<Contact>(Contact(displayName, phoneNumber))).also {
                            contactListState.value = it
                        }

                    }
                }

            }
        }
    }

}