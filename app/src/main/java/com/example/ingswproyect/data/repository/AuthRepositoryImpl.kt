package com.example.ingswproyect.data.repository

import com.example.ingswproyect.domain.model.Usuario
import com.example.ingswproyect.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Result<Usuario> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
                ?: return Result.failure(Exception("No se pudo obtener el usuario de Firebase."))

            val usuario = Usuario(
                userId = user.uid,
                email = user.email ?: email,
                username = user.displayName ?: email.substringBefore("@")
            )

            Result.success(usuario)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<Usuario> {
        return try {
            // 1) Autenticación con Google en Firebase
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user
                ?: return Result.failure(Exception("No se pudo obtener el usuario de Firebase."))

            val uid = user.uid
            val email = user.email ?: ""
            val username = user.displayName ?: email.substringBefore("@")

            // Usuario base por si Firestore falla
            val baseUsuario = Usuario(
                userId = uid,
                email = email,
                username = username
            )

            // 2) Sincronizar con Firestore, pero sin romper si está offline
            return try {
                val docRef = firestore.collection("usuarios").document(uid)
                val snap = docRef.get().await()

                val usuario = if (!snap.exists()) {
                    val data = mapOf(
                        "userId" to baseUsuario.userId,
                        "email" to baseUsuario.email,
                        "username" to baseUsuario.username
                    )
                    docRef.set(data, SetOptions.merge()).await()
                    baseUsuario
                } else {
                    val data = snap.data ?: emptyMap<String, Any>()
                    Usuario(
                        userId = data["userId"] as? String ?: uid,
                        email = data["email"] as? String ?: email,
                        username = data["username"] as? String ?: username
                    )
                }

                Result.success(usuario)
            } catch (e: Exception) {
                // Si falla Firestore (offline, permisos etc.), seguimos con el usuario autenticado
                Result.success(baseUsuario)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isEmailVerified(email: String): Boolean {
        val current = auth.currentUser
        return current?.email == email && current.isEmailVerified
    }
}
