package pl.mw.citycall.ui.activities

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.mw.citycall.FireBaseRepository

class MainViewModel: ViewModel() {

    private val repo = FireBaseRepository()

    private val _user = MutableStateFlow(getCurrentUser())
    val user = _user.asStateFlow()

    private fun getCurrentUser(): FirebaseUser? = repo.getCurrentUser()

    fun registerNewUserByEmail(name: String, email: String, paswword: String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val newUser = repo.registerNewUserByEmail(name, email, paswword)
        }
    }

    fun loginUser(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            _user.emit(repo.loginUser(email, password))
        }
    }

}