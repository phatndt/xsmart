package com.example.xsmart.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.app
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth

class AuthenticationRepository {
    suspend fun loginByGoogle(idToken: String) {
        Firebase.auth.signInWithCredential(GoogleAuthProvider.credential(idToken, null))
    }
}