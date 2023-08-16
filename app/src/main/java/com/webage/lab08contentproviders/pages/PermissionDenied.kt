package com.webage.lab08contentproviders.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDenied() {
    Text(
        "Permission denied", modifier = Modifier.padding(10.dp),
        fontWeight = FontWeight.Bold)
}