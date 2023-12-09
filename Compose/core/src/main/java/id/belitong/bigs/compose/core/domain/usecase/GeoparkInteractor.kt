package id.belitong.bigs.compose.core.domain.usecase

import id.belitong.bigs.compose.core.domain.repository.IGeoparkRepository
import javax.inject.Inject

class GeoparkInteractor @Inject constructor(
    private val geoparkRepository: IGeoparkRepository,
) : GeoparkUseCase {
    override fun getGeosites() = geoparkRepository.getGeosites()
    override fun getBiodiversities() = geoparkRepository.getBiodiversities()
    override fun getPlants() = geoparkRepository.getPlants()
    override fun getOrders() = geoparkRepository.getOrders()
    override fun getReports() = geoparkRepository.getReports()

}