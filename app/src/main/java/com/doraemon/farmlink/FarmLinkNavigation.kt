package com.doraemon.farmlink

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doraemon.farmlink.Screens.HomeScreen
import com.doraemon.farmlink.Screens.LoginScreen
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
        composable("home"){
            HomeScreen(modifier, navController, authViewModel)
        }
    })
}