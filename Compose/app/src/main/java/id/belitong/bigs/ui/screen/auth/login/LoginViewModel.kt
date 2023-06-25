package id.belitong.bigs.ui.screen.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Login
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _result = MutableStateFlow<Resource<Login>>(Resource.Loading())
    val result: StateFlow<Resource<Login>> get() = _result

    private val _token = mutableStateOf("")
    val token get() = _token.value

    init {
        viewModelScope.launch {
            authUseCase.getAuthToken().collect { token ->
                _token.value = token
            }
        }
    }

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

}