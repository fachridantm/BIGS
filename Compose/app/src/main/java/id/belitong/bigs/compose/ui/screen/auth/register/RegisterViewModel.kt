package id.belitong.bigs.compose.ui.screen.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.domain.model.Register
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    private val _result = MutableLiveData<Resource<Register>>()
    val result: LiveData<Resource<Register>> get() = _result

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            authUseCase.registerUser(name, email, password)
                .collect { result ->
                    _result.value = result
                }
        }
    }
}