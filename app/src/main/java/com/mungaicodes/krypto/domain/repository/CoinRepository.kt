package com.mungaicodes.krypto.domain.repository

import com.mungaicodes.krypto.data.remote.dto.CoinDetailDto
import com.mungaicodes.krypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}