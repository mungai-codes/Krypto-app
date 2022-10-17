package com.mungaicodes.krypto.presentation.coin_list

import com.mungaicodes.krypto.domain.model.Coin

data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
