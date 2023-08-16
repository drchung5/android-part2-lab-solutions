package com.webage.lab08contentproviders.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webage.lab08contentproviders.ContactViewModel
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ContactsPage(vm :ContactViewModel = viewModel()) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Text(
            "Contacts",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold
        )

        vm.loadContacts(LocalContext.current)

        for (contact in vm.contactListState.value) {
            Text(
                "${contact.phoneNumber}   ${contact.name} ",
                modifier = Modifier.padding(10.dp, 2.dp)
            )
        }
    }
}