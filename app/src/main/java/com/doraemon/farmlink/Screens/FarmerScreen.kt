package com.doraemon.farmlink.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doraemon.farmlink.FarmerScreens.AddProduceScreen
import com.doraemon.farmlink.FarmerScreens.HomeScreen
import com.doraemon.farmlink.FarmerScreens.ProfileScreen
import com.doraemon.farmlink.FarmerScreens.TransactionScreen
import com.doraemon.farmlink.authViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmerScreen(navController: NavHostController) {
    val navController = rememberNavController() // Create NavController instance
    val customGreen = Color(0xFF3CB62F)

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
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = customGreen,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        NavigationContentfarmer(navController, Modifier.padding(paddingValues))
    }
}

@Composable
fun NavigationContentfarmer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "farmerHome", // Default screen
        modifier = modifier // Apply padding from Scaffold
    ) {
        composable("farmerHome") { HomeScreen(navController) }
        composable("produce") { AddProduceScreen(navController) }
        composable("transaction") { TransactionScreen(navController) }
        composable("farmerProfile") { ProfileScreen(navController, authViewModel()) }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val customGreen = Color(0xFF3CB62F)
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = customGreen,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "farmerHome",
            onClick = {
                if (currentRoute != "farmerHome") navController.navigate("farmerHome")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Add Produce") },
            label = { Text("Add") },
            selected = currentRoute == "produce",
            onClick = {
                if (currentRoute != "produce") navController.navigate("produce")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Transactions") },
            label = { Text("Orders") },
            selected = currentRoute == "transaction",
            onClick = {
                if (currentRoute != "transaction") navController.navigate("transaction")
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == "farmerProfile",
            onClick = {
                if (currentRoute != "farmerProfile") navController.navigate("farmerProfile")
            }
        )
    }
}
