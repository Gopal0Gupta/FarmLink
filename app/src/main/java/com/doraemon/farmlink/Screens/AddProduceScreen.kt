package com.doraemon.farmlink.Screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.doraemon.farmlink.FarmerViewModel
import com.doraemon.farmlink.authViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduceScreen(modifier: Modifier, navController: NavController, authViewModel: authViewModel, farmerViewModel: FarmerViewModel) {
        val context = LocalContext.current
        var produceName by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Add Produce", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            // Produce Name Input
            TextField(
                value = produceName,
                onValueChange = { produceName = it },
                label = { Text("Produce Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Price Input
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button to upload an image
            Button(
                onClick = {
                    // Call function to select image from gallery
                    farmerViewModel.selectImageFromGallery(context) { uri ->
                        imageUri = uri
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Upload Produce Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show image preview if selected
            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to submit the form
            Button(
                onClick = {
                    // Call function to add produce
                    if (produceName.isNotEmpty() && price.isNotEmpty() && imageUri != null) {
                        farmerViewModel.addProduceToFirebase(
                            produceName = produceName,
                            price = price,
                            imageUri = imageUri!!,
                            context = context
                        )
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit")
            }
        }
    }