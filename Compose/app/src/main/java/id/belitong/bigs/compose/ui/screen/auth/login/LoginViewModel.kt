package id.belitong.bigs.compose.ui.screen.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.data.Resource.Companion.init
import id.belitong.bigs.compose.core.data.Resource.Companion.loading
import id.belitong.bigs.compose.core.data.Resource.Companion.success
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

    private val _token = MutableLiveData<Resource<String>>()
    val token: LiveData<Resource<String>> get() = _token

    init {
        _result.value = init()
        _token.value = init()
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _result.value = loading()
            authUseCase.loginUser(email, password).collect {
                _result.value = it
            }
        }
    }

    fun saveSession(token: String, user: User) {
        viewModelScope.launch {
            authUseCase.saveSession(token, user)
        }
    }

    fun getToken() {
        viewModelScope.launch {
            _token.value = loading()
            authUseCase.getAuthToken().collect {
                _token.value = success(it)
            }
        }
    }
}