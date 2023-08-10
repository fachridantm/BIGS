package id.belitong.bigs.compose.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun getName() = authUseCase.getName().asLiveData()
    fun deleteSession() {
        viewModelScope.launch {
            authUseCase.deleteSession()
        }
    }
}