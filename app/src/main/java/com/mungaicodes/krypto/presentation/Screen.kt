package com.mungaicodes.krypto.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list")
    object CoinDetailsScreen : Screen("coin_details")
}
