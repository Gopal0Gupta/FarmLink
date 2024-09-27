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
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.FarmerNavigation
import com.doraemon.farmlink.FarmerViewModel
import com.doraemon.farmlink.authViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerScreen(authViewModel: authViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(
                            text = "FarmLink",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold ) },
                    backgroundColor = Color.Green,
                    contentColor = Color.White
                )
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { paddingValues ->
            FarmerNavigation(navController, paddingValues)
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.Green,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false, // Later, add state management for selection
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Add Produce") },
            label = { Text("Add") },
            selected = false,
            onClick = { navController.navigate("produce") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Transactions") },
            label = { Text("Orders") },
            selected = false,
            onClick = { navController.navigate("transaction") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
    }
}

/*
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
       */