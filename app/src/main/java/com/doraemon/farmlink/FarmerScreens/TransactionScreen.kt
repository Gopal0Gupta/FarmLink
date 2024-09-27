package com.doraemon.farmlink.FarmerScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransactionScreen() {
    LazyColumn {
        items(7) { index ->
            Text(text = "Transaction Item $index", modifier = Modifier.padding(16.dp))
        }
    }
}