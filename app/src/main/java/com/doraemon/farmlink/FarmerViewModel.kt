package com.doraemon.farmlink

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class FarmerViewModel : ViewModel(){

    fun selectImageFromGallery(context: Context, onImageSelected: (Uri?) -> Unit) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val activity = context as Activity
        activity.startActivityForResult(intent, 100)
        onImageSelected(intent.data)
    }

    fun uploadImageToFirebaseStorage(uri: Uri, context: Context, onImageUploaded: (String) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("produce_images/${UUID.randomUUID()}")
        storageRef.putFile(uri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    // Pass the image URL after uploading
                    onImageUploaded(downloadUrl.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()
            }
    }

    fun addProduceToFirebase(produceName: String, price: String, imageUri: Uri, context: Context) {
        uploadImageToFirebaseStorage(imageUri, context) { imageUrl ->
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            val db = FirebaseFirestore.getInstance()

            val produceData = hashMapOf(
                "name" to produceName,
                "price" to price,
                "imageUrl" to imageUrl
            )

            if (uid != null) {
                db.collection("farmers").document(uid)
                    .collection("produce").add(produceData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Produce added successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Failed to add produce", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
