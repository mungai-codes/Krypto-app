package com.mungaicodes.krypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.krypto.common.Resource
import com.mungaicodes.krypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    //should be able to modify content of our state class from composables

    private val _state = MutableStateFlow(CoinsListState())
    val state = _state.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(coins = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(error = result.message ?: "Unexpected Error Occured")
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}