package id.belitong.bigs.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.data.Resource.Companion.init
import id.belitong.bigs.core.data.Resource.Companion.loading
import id.belitong.bigs.core.data.Resource.Companion.success
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.model.Plant
import id.belitong.bigs.core.domain.model.Report
import id.belitong.bigs.core.domain.usecase.AuthUseCase
import id.belitong.bigs.core.domain.usecase.GeoparkUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val geoparkUseCase: GeoparkUseCase
) : ViewModel() {
    private val _name = MutableLiveData<Resource<String>>()
    val name: LiveData<Resource<String>> get() = _name

    private val _biodiversities = MutableLiveData<Resource<List<Biodiversity>>>()
    val biodiversities: LiveData<Resource<List<Biodiversity>>> get() = _biodiversities

    private val _geosites = MutableLiveData<Resource<List<Geosite>>>()
    val geosites: LiveData<Resource<List<Geosite>>> get() = _geosites

    private val _plant = MutableLiveData<Resource<Plant>>()
    val plant: LiveData<Resource<Plant>> get() = _plant

    private val _orders = MutableLiveData<Resource<List<Order>>>()
    val orders: LiveData<Resource<List<Order>>> get() = _orders

    private val _reports = MutableLiveData<Resource<List<Report>>>()
    val reports: LiveData<Resource<List<Report>>> get() = _reports

    init {
        _name.value = init()
        _biodiversities.value = init()
        _geosites.value = init()
        _plant.value = init()
        _orders.value = init()
        _reports.value = init()
    }

    fun getName() {
        viewModelScope.launch {
            _name.value = loading()
            authUseCase.getName().collect {
                _name.value = success(it)
            }
        }
    }

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

    fun getOrders() {
        viewModelScope.launch {
            _orders.value = loading()
            geoparkUseCase.getOrders().collect {
                _orders.value = it
            }
        }
    }

    fun getReports() {
        viewModelScope.launch {
            _reports.value = loading()
            geoparkUseCase.getReports().collect {
                _reports.value = it
            }
        }
    }
}