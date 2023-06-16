package id.belitong.bigs.core.domain.usecase

import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val IMainRepository: IMainRepository,
) : MainUseCase {
    override fun getAllGeosites(): Flow<Resource<List<Geosite>>> =
        IMainRepository.getAllGeosites()

    override fun getAllBiodiversity(): Flow<Resource<List<Biodiversity>>> =
        IMainRepository.getAllBiodiversity()
}