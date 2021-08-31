package com.waqas.starshipapp.domain.home.usecase

import com.waqas.starshipapp.data.common.utils.WrappedListResponse
import com.waqas.starshipapp.data.home.remote.dto.StarshipResponse
import com.waqas.starshipapp.domain.base.BaseResult
import com.waqas.starshipapp.domain.home.HomeRepository
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarshipsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun invoke(): Flow<BaseResult<List<StarshipEntity>, WrappedListResponse<StarshipResponse>>> {
        return homeRepository.getStarships()
    }
}