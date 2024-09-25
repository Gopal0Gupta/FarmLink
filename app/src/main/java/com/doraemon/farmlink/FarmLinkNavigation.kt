package com.doraemon.farmlink

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doraemon.farmlink.Screens.BuyerScreen
import com.doraemon.farmlink.Screens.FarmerScreen
import com.doraemon.farmlink.Screens.LoginScreen
import com.doraemon.farmlink.Screens.RoleScreen
import com.doraemon.farmlink.Screens.SignupScreen

@Composable
fun FarmLinkNavigation(modifier: Modifier, authViewModel: authViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignupScreen(modifier, navController, authViewModel)
        }
        composable("role"){
            RoleScreen(modifier, navController, authViewModel)
        }
        composable("farmer"){
            FarmerScreen(modifier, navController, authViewModel)
        }
        composable("buyer"){
            BuyerScreen(modifier, navController, authViewModel)
        }
    })
}