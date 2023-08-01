package id.belitong.bigs.compose.ui.screen.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.domain.model.Login
import id.belitong.bigs.compose.core.domain.model.User
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _result = MutableLiveData<Resource<Login>>()
    val result: LiveData<Resource<Login>> get() = _result

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.loginUser(email, password).collect { result ->
                _result.value = result
            }
        }
    }

    fun saveSession(token: String, user: User) {
        viewModelScope.launch {
            authUseCase.saveSession(token, user)
        }
    }

    fun getToken() = authUseCase.getAuthToken().asLiveData()

}