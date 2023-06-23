package id.belitong.bigs.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

//    private val _result = MutableLiveData<Resource<Login>>()
//    val result: LiveData<Resource<Login>> get() = _result
//
//    fun loginUser(email: String, password: String) {
//        viewModelScope.launch {
//            repository.loginUser(email, password).collect { result ->
//                _result.value = result
//            }
//        }
//    }
//
//    fun saveSession(token: String, user: User) {
//        viewModelScope.launch {
//            repository.saveSession(token, user)
//        }
//    }
//
//    fun getToken() = repository.getAuthToken().asLiveData()
}