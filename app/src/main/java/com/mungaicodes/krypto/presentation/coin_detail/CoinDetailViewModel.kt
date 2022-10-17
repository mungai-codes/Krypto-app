package com.mungaicodes.krypto.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.krypto.common.Constants
import com.mungaicodes.krypto.common.Resource
import com.mungaicodes.krypto.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    //should be able to modify content of our state class from composables

    private val _state = MutableStateFlow(CoinsDetailState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(coin = result.data)
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