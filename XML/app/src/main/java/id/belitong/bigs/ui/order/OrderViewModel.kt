package id.belitong.bigs.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.domain.usecase.MainUseCase
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    fun getAllOrder() = mainUseCase.getAllOrder().asLiveData()
}