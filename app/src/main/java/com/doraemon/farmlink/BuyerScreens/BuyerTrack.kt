package com.doraemon.farmlink.BuyerScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BuyerTrack() {
    LazyColumn {
        items(7) { index ->
            Text(text = "Your Order $index", modifier = Modifier.padding(16.dp))
        }
    }
}