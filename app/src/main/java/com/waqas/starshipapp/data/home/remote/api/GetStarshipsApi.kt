package com.waqas.starshipapp.data.home.remote.api

import com.waqas.starshipapp.data.common.utils.WrappedListResponse
import com.waqas.starshipapp.data.home.remote.dto.StarshipResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface GetStarshipsApi {

    @GET("starships/")
    suspend fun getStarships(): Response<WrappedListResponse<StarshipResponse>>
}