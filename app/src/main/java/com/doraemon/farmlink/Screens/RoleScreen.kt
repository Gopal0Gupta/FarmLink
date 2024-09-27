package com.doraemon.farmlink.Screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doraemon.farmlink.R
import com.doraemon.farmlink.authViewModel

@Composable
fun RoleScreen(
    modifier: Modifier,
    navController: NavController,
    authViewModel: authViewModel
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
        Text(
            text = "Select Your Role",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier.height(25.dp))
        Column(
            modifier = Modifier.clickable {
                authViewModel.saveUserRoleToFirestore(context,"farmer",authViewModel.Name)
                navController.navigate("farmer")
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.farmericon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )
            Text(
                text = "Farmer",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier.height(15.dp))
        Column(
            modifier = Modifier.clickable {
                authViewModel.saveUserRoleToFirestore(context,"buyer", authViewModel.Name)
                navController.navigate("buyer")
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.buyericon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )
            Text(
                text = "Buyer",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}