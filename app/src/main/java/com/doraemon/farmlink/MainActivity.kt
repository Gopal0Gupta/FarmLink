package com.doraemon.farmlink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.doraemon.farmlink.ui.theme.FarmLinkTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : authViewModel by viewModels()
        val farmerViewModel: FarmerViewModel by viewModels()
        setContent {
            FarmLinkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FarmLinkNavigation(modifier = Modifier.padding(innerPadding), authViewModel = authViewModel,farmerViewModel)
                }
            }
        }
    }
}