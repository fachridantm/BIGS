package id.belitong.bigs.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.domain.usecase.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    fun getName() = authUseCase.getName().asLiveData()

}