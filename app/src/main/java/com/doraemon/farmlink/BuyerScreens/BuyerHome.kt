package com.doraemon.farmlink.BuyerScreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.doraemon.farmlink.BuyerScreens.Produce
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BuyerHome() {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    var produceList by remember { mutableStateOf<List<Produce>>(emptyList()) }

    // Fetch all produce from Firestore
    LaunchedEffect(Unit) {
        db.collection("produce") // Replace with your collection name
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Produce>()
                for (document in result) {
                    val produce = Produce(
                        name = document.getString("name") ?: "",
                        price = document.getString("price") ?: "",
                        description = document.getString("description") ?: "",
                        imageUrl = document.getString("imageUrl") ?: "",
                        farmerId = document.getString("farmerId") ?: "" // Optional: to identify the farmer
                    )
                    list.add(produce)
                }
                produceList = list
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error in Fetching Data", Toast.LENGTH_SHORT).show()
            }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(produceList) { produce ->
            ProduceCard(produce) // A Composable to display each produce item
        }
    }
}

@Composable
fun ProduceCard(produce: Produce) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (produce.imageUrl.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(produce.imageUrl),
                contentDescription = "Produce Image",
                modifier = Modifier.fillMaxWidth().height(300.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                "Name: ${produce.name}",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Price: ${produce.price}",
                modifier = Modifier.weight(1f)
            )
        }
        Text(text = "Description: ${produce.description}")
    }
}
data class Produce(
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val farmerId: String // Optional, to identify the farmer
)