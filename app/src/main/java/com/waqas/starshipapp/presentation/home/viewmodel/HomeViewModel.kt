package com.waqas.starshipapp.presentation.home.viewmodel

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqas.starshipapp.data.common.utils.WrappedListResponse
import com.waqas.starshipapp.data.home.remote.dto.StarshipResponse
import com.waqas.starshipapp.domain.base.BaseResult
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import com.waqas.starshipapp.domain.home.usecase.GetStarshipsUseCase
import com.waqas.starshipapp.domain.home.usecase.GetUpdatedListUseCase
import com.waqas.starshipapp.domain.home.usecase.SetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getStarshipsUseCase: GetStarshipsUseCase, private val setFavoriteUseCase: SetFavoriteUseCase, private val getUpdatedListUseCase: GetUpdatedListUseCase): ViewModel() {
    private val state = MutableStateFlow<HomeActivityState>(HomeActivityState.Init)
    val mState : StateFlow<HomeActivityState> get() = state

    private fun setLoading(){
        state.value = HomeActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = HomeActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = HomeActivityState.ShowToast(message)
    }

    private fun successResponse(starships: List<StarshipEntity>){
        state.value = HomeActivityState.SuccessResponse(starships)
    }

    private fun errorResponse(rawResponse: WrappedListResponse<StarshipResponse>){
        state.value = HomeActivityState.ErrorResponse(rawResponse)
    }

    fun getStarships(){
        viewModelScope.launch {
            getStarshipsUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> successResponse(result.data)
                        is BaseResult.Error -> errorResponse(result.rawResponse)
                    }
                }
        }
    }

    fun setFavourite(name: String, set: Boolean){
        viewModelScope.launch {
            setFavoriteUseCase.invoke(name,set)
                .onStart {
                    setLoading()
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> successResponse(result.data)
                    }
                }
        }
    }

    fun getUpdatedList(): List<StarshipEntity>{
        return getUpdatedListUseCase.invoke()
    }

}

sealed class HomeActivityState{
    object Init: HomeActivityState()
    data class IsLoading(val isLoading: Boolean): HomeActivityState()
    data class ShowToast(val message: String): HomeActivityState()
    data class SuccessResponse(val starships: List<StarshipEntity>): HomeActivityState()
    data class ErrorResponse(val rawResponse: WrappedListResponse<StarshipResponse>): HomeActivityState()
}