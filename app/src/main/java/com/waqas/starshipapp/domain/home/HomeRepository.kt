package com.waqas.starshipapp.domain.home

import com.waqas.starshipapp.data.common.utils.WrappedListResponse
import com.waqas.starshipapp.data.home.remote.dto.StarshipResponse
import com.waqas.starshipapp.domain.base.BaseResult
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    var allStarships: List<StarshipEntity>
    suspend fun getStarships(): Flow<BaseResult<List<StarshipEntity>, WrappedListResponse<StarshipResponse>>>
    suspend fun setFavourite(index: Int, set: Boolean): Flow<BaseResult<List<StarshipEntity>, WrappedListResponse<StarshipResponse>>>
    fun getUpdatedList(): List<StarshipEntity>
}