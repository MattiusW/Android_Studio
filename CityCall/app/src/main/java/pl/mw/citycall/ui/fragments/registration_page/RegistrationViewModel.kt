package pl.mw.citycall.ui.fragments.registration_page

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.mw.citycall.FireBaseRepository

class RegistrationViewModel : ViewModel() {
    private val repo = FireBaseRepository()

    fun registerNewUserByEmail(email: String, password: String, name: String){
        CoroutineScope(Dispatchers.IO).launch {
            repo.registerNewUserByEmail(email, password, name)
        }
    }
}