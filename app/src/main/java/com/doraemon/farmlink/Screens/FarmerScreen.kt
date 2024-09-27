package com.doraemon.farmlink.Screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.FarmerViewModel
import com.doraemon.farmlink.authViewModel

@Composable
fun FarmerScreen(
    modifier: Modifier,
    navController: NavController,
    authViewModel: authViewModel,
    farmerViewModel: FarmerViewModel
) {
    val context = LocalContext.current
    BackHandler {
        (context as? ComponentActivity)?.finish() // Close the app
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val authState = authViewModel.authState.observeAsState()
        val context = LocalContext.current
        LaunchedEffect(authState.value){
            when(authState.value){
                is AuthState.unauthenticated -> navController.navigate("login")
                else -> Unit
            }
        }
        Text(
            text = "Farmer Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = {
            navController.navigate("addproduce")
        }) {
            Text(text = "Upload Produce")
        }
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign Out")
        }
    }
}
