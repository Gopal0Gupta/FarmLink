package com.doraemon.farmlink

import android.os.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider

class authViewModel : ViewModel() {
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

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

    fun signup(email : String, password : String){
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

}
sealed class AuthState{
    object authenticated : AuthState()
    object unauthenticated : AuthState()
    object loading : AuthState()
    data class error(val message: String) : AuthState()
}