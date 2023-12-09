package id.belitong.bigs.compose.ui.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.compose.core.data.Resource
import id.belitong.bigs.compose.core.data.Resource.Companion.init
import id.belitong.bigs.compose.core.data.Resource.Companion.loading
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.domain.model.Report
import id.belitong.bigs.compose.core.domain.usecase.GeoparkUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val geoparkUsecase: GeoparkUseCase
) : ViewModel() {

    private val _orders = MutableLiveData<Resource<List<Order>>>()
    val orders: LiveData<Resource<List<Order>>> get() = _orders

    private val _reports = MutableLiveData<Resource<List<Report>>>()
    val reports: LiveData<Resource<List<Report>>> get() = _reports

    init {
        _orders.value = init()
        _reports.value = init()
    }

    fun getOrders() {
        viewModelScope.launch {
            _orders.value = loading()
            geoparkUsecase.getOrders().collect {
                _orders.value = it
            }
        }
    }

    fun getReports() {
        viewModelScope.launch {
            _reports.value = loading()
            geoparkUsecase.getReports().collect {
                _reports.value = it
            }
        }
    }
}