package com.doraemon.farmlink.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.BuyerScreens.BuyerCart
import com.doraemon.farmlink.BuyerScreens.BuyerHome
import com.doraemon.farmlink.BuyerScreens.BuyerProfile
import com.doraemon.farmlink.BuyerScreens.BuyerTrack
import com.doraemon.farmlink.FarmerScreens.ProfileScreen
import com.doraemon.farmlink.authViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerScreen(navController: NavHostController) {
    val navController = rememberNavController() // Create NavController instance
    val customBlue = Color(0xFF329AF5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FarmLink",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = customBlue,
                contentColor = Color.White
            )
        },
        bottomBar = {
            BottomNavigationBarBuyer(navController)
        }
    ) { paddingValues ->
        NavigationContent(navController, Modifier.padding(paddingValues))
    }
}

@Composable
fun NavigationContent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home", // Default screen
        modifier = modifier // Apply padding from Scaffold
    ) {
        composable("home") { BuyerHome(navController, authViewModel()) }
        composable("add-cart") { BuyerCart(navController) }
        composable("track") { BuyerTrack(navController) }
        composable("profile") { BuyerProfile(navController, authViewModel()) }
    }
}

@Composable
fun BottomNavigationBarBuyer(navController: NavHostController) {
    val customBlue = Color(0xFF329AF5)
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = customBlue,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") navController.navigate("home")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") },
            selected = currentRoute == "add-cart",
            onClick = {
                if (currentRoute != "add-cart") navController.navigate("add-cart")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Track Order") },
            label = { Text("Track") },
            selected = currentRoute == "track",
            onClick = {
                if (currentRoute != "track") navController.navigate("track")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == "profile",
            onClick = {
                if (currentRoute != "profile") navController.navigate("profile")
            }
        )
    }
}
