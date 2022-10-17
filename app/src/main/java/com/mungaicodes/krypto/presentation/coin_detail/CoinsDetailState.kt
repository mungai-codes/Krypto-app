package com.mungaicodes.krypto.presentation.coin_detail

import com.mungaicodes.krypto.domain.model.CoinDetail

data class CoinsDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
