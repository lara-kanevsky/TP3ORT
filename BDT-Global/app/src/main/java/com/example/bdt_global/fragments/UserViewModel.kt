package com.example.bdt_global.fragments

import androidx.lifecycle.ViewModel
import com.example.bdt_global.entities.User
import com.example.bdt_global.entities.responseEntities.ResultsResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserViewModel : ViewModel() {

    private var auth = Firebase.auth
    private var store = Firebase.firestore

    fun register(
        name: String,
        surname: String,
        dni: Int,
        mail: String,
        password: String
    ): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(mail, password).addOnSuccessListener {
            registerInStore(auth.currentUser!!.uid, name, surname, dni, mail)
        }
    }

    private fun registerInStore(
        newUserUid: String,
        name: String,
        surname: String,
        dni: Int,
        mail: String
    ) {
        store.collection("users").document(newUserUid)
            .set(User(newUserUid, name, surname, dni, mail))
    }

    fun logIn(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun getCurrentUser(callback: (User?) -> Unit) {
        val currentUserUid = auth.currentUser?.uid

        if (currentUserUid != null) {
            store.collection("users").document(currentUserUid).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val user = documentSnapshot.toObject(User::class.java)
                        callback(user)
                    }
                }
        }
    }

    fun saveResults(results: ResultsResponse) {
        if (results != null) {
            getCurrentUser { user ->
                user!!.addResults(results)
                val userDocRef = store.collection("users").document(auth.currentUser!!.uid)
                userDocRef.update("results", user.getResults())
            }
        }
    }

    fun logOut() {
        auth.signOut()
    }

}
