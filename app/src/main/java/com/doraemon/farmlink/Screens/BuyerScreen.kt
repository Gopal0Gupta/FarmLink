package com.doraemon.farmlink.Screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.authViewModel

@Composable
fun BuyerScreen(
    modifier: Modifier,
    navController: NavController,
    authViewModel: authViewModel
) {
    val context = LocalContext.current
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
            text = "Buyer Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign Out")
        }
    }
}