package com.doraemon.farmlink

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.doraemon.farmlink.ui.theme.FarmLinkTheme
import com.razorpay.PaymentResultListener

class MainActivity : ComponentActivity(), PaymentResultListener {
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

    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        // Payment successful
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
        Log.d("Razorpay", "Payment Successful: $razorpayPaymentID")
    }

    override fun onPaymentError(code: Int, description: String?) {
        // Payment failed
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
        Log.e("Razorpay", "Payment Failed: $description")
    }
}