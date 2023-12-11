package id.belitong.bigs.compose.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.data.Resource.Companion.init
import id.belitong.bigs.compose.core.data.Resource.Companion.loading
import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.domain.model.Plant
import id.belitong.bigs.compose.core.domain.usecase.AuthUseCase
import id.belitong.bigs.compose.core.domain.usecase.GeoparkUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val geoparkUseCase: GeoparkUseCase
) : ViewModel() {
    private val _biodiversities = MutableLiveData<Resource<List<Biodiversity>>>()
    val biodiversities: LiveData<Resource<List<Biodiversity>>> get() = _biodiversities

    private val _geosites = MutableLiveData<Resource<List<Geosite>>>()
    val geosites: LiveData<Resource<List<Geosite>>> get() = _geosites

    private val _plant = MutableLiveData<Resource<Plant>>()
    val plant: LiveData<Resource<Plant>> get() = _plant

    init {
        _biodiversities.value = init()
        _geosites.value = init()
        _plant.value = init()
    }

    fun getName() = authUseCase.getName().asLiveData()

    fun getBiodiversities() {
        viewModelScope.launch {
            _biodiversities.value = loading()
            geoparkUseCase.getBiodiversities().collect {
                _biodiversities.value = it
            }
        }
    }

    fun getGeosites() {
        viewModelScope.launch {
            _geosites.value = loading()
            geoparkUseCase.getGeosites().collect {
                _geosites.value = it
            }
        }
    }

    fun getPlant() {
        viewModelScope.launch {
            _plant.value = loading()
            geoparkUseCase.getPlant().collect {
                _plant.value = it
            }
        }
    }
}