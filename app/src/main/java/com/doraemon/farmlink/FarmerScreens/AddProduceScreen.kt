package com.doraemon.farmlink.FarmerScreens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.doraemon.farmlink.R
import com.google.firebase.storage.FirebaseStorage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduceScreen() {
    var produceName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember{
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri? ->
        imageUri = uri
    }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter: Painter = if (imageUri != null){
                rememberAsyncImagePainter(imageUri)
            }else{
                painterResource(id = R.drawable.gallery)
            }
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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description Input
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .border(
                        width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        launcher.launch("image/*")
                    }
                )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (imageUri != null){
                    imageUri.let { uri ->  
                        uploadImageToFirebase(uri,context)
                    }
                }else{
                    Toast.makeText(context,"Please Upload Image From Gallery",Toast.LENGTH_SHORT).show()
                }
            }, shape = RoundedCornerShape(5.dp)) {
                Text(text = "Upload")
            }
        }
    }
}

fun uploadImageToFirebase(uri: Uri?, context: Context) {
    val storage = FirebaseStorage.getInstance()
    val storageRefrence = storage.reference
    val imageRefrence = storageRefrence.child("image/"+uri!!.lastPathSegment)

    val uploadTask = uri.let { imageRefrence.putFile(it) }

    uploadTask.addOnSuccessListener {
        Toast.makeText(context,"Image Upload Complete",Toast.LENGTH_SHORT).show()
    }.addOnFailureListener{
        Toast.makeText(context,"Image Upload Complete",Toast.LENGTH_SHORT).show()
    }
}

/*
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
*/