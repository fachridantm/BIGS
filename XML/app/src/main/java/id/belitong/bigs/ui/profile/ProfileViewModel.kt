package id.belitong.bigs.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    fun getName() = authUseCase.getName().asLiveData()

    fun getEmail() = "dummy@gmail.com"

    fun getPhoneNumber() = "081234567890"

    fun getUserImage() = ""

    fun deleteSession() {
        viewModelScope.launch {
            authUseCase.deleteSession()
        }
    }

}