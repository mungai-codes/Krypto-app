package com.mungaicodes.krypto.data.repository

import com.mungaicodes.krypto.data.remote.CoinPaprikaApi
import com.mungaicodes.krypto.data.remote.dto.CoinDetailDto
import com.mungaicodes.krypto.data.remote.dto.CoinDto
import com.mungaicodes.krypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}