package pl.mw.citycall

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FireBaseRepository {
    
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun getCurrentUser() = auth.currentUser

    suspend fun loginUser(email: String, password: String): FirebaseUser?
    {
        return auth.signInWithEmailAndPassword(email, password)
            .await()
            .user
    }
    
    suspend fun registerNewUserByEmail(email: String, password: String, name: String): FirebaseUser? {
        val firebaseUser = auth.createUserWithEmailAndPassword(email, password)
            .await()
            .user
        
        addNewUserToDatabase(firebaseUser, name)

        return firebaseUser
    }

    private suspend fun addNewUserToDatabase(firebaseUser: FirebaseUser?, name: String) {
        firebaseUser ?: throw Exception("User cannot be null!")

        val user = UserModel(
            id = firebaseUser.uid,
            name = name,
            email = firebaseUser.email ?: "")

        db.collection("users")
            .add(user)
            .await()
    }
}