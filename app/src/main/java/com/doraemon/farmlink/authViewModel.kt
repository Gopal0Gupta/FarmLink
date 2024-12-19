package com.doraemon.farmlink

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.Checkout
import org.json.JSONObject

class authViewModel : ViewModel() {
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState
    var Name by mutableStateOf("")
    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.unauthenticated
        }else{
            _authState.value = AuthState.authenticated
        }
    }

    fun login(email : String, password : String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.error("Email & Password can't be Empty")
            return
        }
        _authState.value = AuthState.loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if (task.isSuccessful){
                    _authState.value = AuthState.authenticated
                }else{
                    _authState.value = AuthState.error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signup(name : String, email : String, password : String){
        Name = name
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.error("Email & Password can't be Empty")
            return
        }
        _authState.value = AuthState.loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if (task.isSuccessful){
                    _authState.value = AuthState.authenticated
                }else{
                    _authState.value = AuthState.error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.unauthenticated
    }
    fun saveUserRoleToFirestore(context : Context, role: String, name: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        if (uid != null) {
            val userData = hashMapOf(
                "role" to role,
                "name" to name
            )
            db.collection("users").document(uid).set(userData)
                .addOnSuccessListener {
                    Toast.makeText(context, "User role saved successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error saving user role", Toast.LENGTH_SHORT).show()
                }
        }
    }
    fun startRazorpayPayment(context: Context, amountInRupees: Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_IRbi2pDDsoh3Zu")

        try {
            val options = JSONObject()
            options.put("name", "FarmLink") // Your app/company name
            options.put("description", "Payment for Goods") // Payment purpose
            options.put("currency", "INR") // Currency type
            options.put("amount", amountInRupees * 100) // Razorpay accepts amount in paise (100 paise = 1 INR)

            // Pre-fill email and contact for the user
            val prefill = JSONObject()
            prefill.put("email", "example@gmail.com")
            prefill.put("contact", "9876543210")
            options.put("prefill", prefill)

            checkout.open(context as Activity?, options)
        } catch (e: Exception) {
            Toast.makeText(context, "Error in starting Razorpay Checkout: $e", Toast.LENGTH_LONG).show()
        }
    }

    fun getUserRoleFromFirestore(context: Context, onRoleRetrieved: (String?) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        if (uid != null) {
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val role = document.getString("role")
                        onRoleRetrieved(role)
                    } else {
                        onRoleRetrieved(null)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error retrieving user role", Toast.LENGTH_SHORT).show()
                    Log.d("Razorpay", "Error retrieving user role: ${e.message}")
                }
        } else {
            onRoleRetrieved(null)
        }
    }
}
sealed class AuthState{
    object authenticated : AuthState()
    object unauthenticated : AuthState()
    object loading : AuthState()
    data class error(val message: String) : AuthState()
}