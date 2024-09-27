package com.doraemon.farmlink.Screens

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.BuyerNavigation
import com.doraemon.farmlink.FarmerNavigation
import com.doraemon.farmlink.FarmerScreens.Produce
import com.doraemon.farmlink.authViewModel
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerScreen() {
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
                        fontWeight = FontWeight.Bold
                    ) },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ) },
            bottomBar = {
                BottomNavigationBarBuyer(navController)
            }
        ) { paddingValues ->
            BuyerNavigation(navController, paddingValues)
        }
    }
}
@Composable
fun BottomNavigationBarBuyer(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = false,
                onClick = { navController.navigate("home") }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.LocationOn, contentDescription = "Track Order") },
                label = { Text("Track") },
                selected = false,
                onClick = { navController.navigate("track") }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                label = { Text("Profile") },
                selected = false,
                onClick = { navController.navigate("profile") }
            )
    }
}