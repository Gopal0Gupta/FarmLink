package com.doraemon.farmlink

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.doraemon.farmlink.FarmerScreens.AddProduceScreen
import com.doraemon.farmlink.FarmerScreens.HomeScreen
import com.doraemon.farmlink.FarmerScreens.ProfileScreen
import com.doraemon.farmlink.FarmerScreens.TransactionScreen

@Composable
fun FarmerNavigation(authViewModel: authViewModel,navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = "home", Modifier.padding(paddingValues)) {
        composable("home") {
            HomeScreen()
        }
        composable("produce") {
            AddProduceScreen()
        }
        composable("transaction") {
            TransactionScreen()
        }
        composable("profile") {
            ProfileScreen(authViewModel)
        }
    }
}