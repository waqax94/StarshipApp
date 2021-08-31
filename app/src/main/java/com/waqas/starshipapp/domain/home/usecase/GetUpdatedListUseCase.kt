package com.waqas.starshipapp.domain.home.usecase

import com.waqas.starshipapp.domain.home.HomeRepository
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import javax.inject.Inject

class GetUpdatedListUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    fun invoke(): List<StarshipEntity>{
        return homeRepository.getUpdatedList()
    }
}