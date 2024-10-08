package com.doraemon.farmlink.FarmerScreens

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
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    var produceList by remember { mutableStateOf<List<Produce>>(emptyList()) }

    // Fetch the produce data from Firestore
    db.collection("produce").get()
        .addOnSuccessListener { result ->
            val list = mutableListOf<Produce>()
            for (document in result) {
                val produce = Produce(
                    name = document.getString("name") ?: "",
                    price = document.getString("price") ?: "",
                    description = document.getString("description") ?: "",
                    imageUrl = document.getString("imageUrl") ?: ""
                )
                list.add(produce)
            }
            produceList = list
        }
        .addOnFailureListener {
            Toast.makeText(context, "Error in Fetching Data", Toast.LENGTH_SHORT).show()
        }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(produceList) { produce ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Your Produce",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = rememberAsyncImagePainter(produce.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Crop: ${produce.name}",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Price: ₹ ${produce.price}/KG",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Description: ${produce.description}")
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
data class Produce(
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String
)