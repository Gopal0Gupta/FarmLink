package com.doraemon.farmlink.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doraemon.farmlink.AuthState
import com.doraemon.farmlink.authViewModel

@Composable
fun RoleScreen(
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
        Text(
            text = "Role Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier.height(20.dp))
        TextButton(onClick = {
            authViewModel.saveUserRoleToFirestore(context,"farmer")
            navController.navigate("farmer")
        }) {
            Text(text = "Farmer")
        }
        Spacer(modifier.height(10.dp))
        TextButton(onClick = {
            authViewModel.saveUserRoleToFirestore(context,"buyer")
            navController.navigate("buyer")
        }) {
            Text(text = "Buyer")
        }
    }
}