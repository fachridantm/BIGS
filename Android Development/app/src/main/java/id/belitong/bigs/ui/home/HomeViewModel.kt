package id.belitong.bigs.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.domain.usecase.AuthUseCase
import id.belitong.bigs.core.domain.usecase.MainUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    fun getName() = authUseCase.getName().asLiveData()

    fun getAllGeosites() = mainUseCase.getAllGeosites().asLiveData()

    fun getAllBiodiversity() = mainUseCase.getAllBiodiversity().asLiveData()
}