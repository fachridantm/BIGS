package id.belitong.bigs.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.usecase.GeoparkUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    private val geoparkUseCase: GeoparkUseCase,
) : ViewModel() {
    private val _biodiversities = MutableLiveData<Resource<List<Biodiversity>>>()
    val biodiversities: LiveData<Resource<List<Biodiversity>>> get() = _biodiversities

    init {
        _biodiversities.value = Resource.init()
    }

    fun getBiodiversities() {
        viewModelScope.launch {
            _biodiversities.value = Resource.loading()
            geoparkUseCase.getBiodiversities().collect {
                _biodiversities.value = it
            }
        }
    }
}