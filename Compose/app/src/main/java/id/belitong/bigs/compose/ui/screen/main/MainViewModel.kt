package id.belitong.bigs.compose.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.belitong.bigs.compose.core.domain.usecase.GeoparkUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val geoparkUsecase: GeoparkUseCase
) : ViewModel() {
    fun getGeosites() = geoparkUsecase.getGeosites().asLiveData()
    fun getBiodiversities() = geoparkUsecase.getBiodiversities().asLiveData()
    fun getPlants() = geoparkUsecase.getPlants().asLiveData()
}