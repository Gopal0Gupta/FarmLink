package com.doraemon.farmlink

import android.content.Context
import android.os.Message
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

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
                    Toast.makeText(context, "Error retrieving user role: ${e.message}", Toast.LENGTH_SHORT).show()
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