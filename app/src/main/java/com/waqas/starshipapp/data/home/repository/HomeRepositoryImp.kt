package com.waqas.starshipapp.data.home.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waqas.starshipapp.data.common.utils.WrappedListResponse
import com.waqas.starshipapp.data.home.remote.api.GetStarshipsApi
import com.waqas.starshipapp.data.home.remote.dto.StarshipResponse
import com.waqas.starshipapp.domain.base.BaseResult
import com.waqas.starshipapp.domain.home.HomeRepository
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val getStarshipsApi: GetStarshipsApi,
) : HomeRepository {
    override lateinit var allStarships: List<StarshipEntity>
    override suspend fun getStarships(): Flow<BaseResult<List<StarshipEntity>, WrappedListResponse<StarshipResponse>>> {
        return flow {
            val response = getStarshipsApi.getStarships()
            if(response.isSuccessful){
                val body = response.body()!!
                val starships = mutableListOf<StarshipEntity>()
                body.results?.forEach{ starshipResponse ->
                    starships.add(
                        StarshipEntity(
                        starshipResponse.MGLT,
                        starshipResponse.cargo_capacity,
                        starshipResponse.consumables,
                        starshipResponse.cost_in_credits,
                        starshipResponse.created,
                        starshipResponse.crew,
                        starshipResponse.edited,
                        starshipResponse.films,
                        starshipResponse.hyperdrive_rating,
                        starshipResponse.length,
                        starshipResponse.manufacturer,
                        starshipResponse.max_atmosphering_speed,
                        starshipResponse.model,
                        starshipResponse.name,
                        starshipResponse.passengers,
                        starshipResponse.pilots,
                        starshipResponse.starship_class,
                        starshipResponse.url
                    )
                    )
                }
                allStarships = starships
                emit(BaseResult.Success(starships))

            }
            else{
                val type = object : TypeToken<WrappedListResponse<StarshipResponse>>(){}.type
                val  err : WrappedListResponse<StarshipResponse> = Gson().fromJson(response.errorBody()!!.charStream(),type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun setFavourite(
        index: Int,
        set: Boolean
    ): Flow<BaseResult<List<StarshipEntity>, WrappedListResponse<StarshipResponse>>> {
        return flow {
            for(i in allStarships.indices){
                if(index == i){
                    allStarships[i].isFavourite = set
                }
            }
            emit(BaseResult.Success(allStarships))
        }
    }

}