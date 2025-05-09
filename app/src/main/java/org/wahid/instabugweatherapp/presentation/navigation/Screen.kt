package org.wahid.instabugweatherapp.presentation.navigation

enum class Destinations{
    HOME,
    DETAILS
}

sealed class Screen(val route: String) {
    //    @Serializable
    data object Home : Screen(route = Destinations.HOME.name)

    //    @Serializable
    data object Detail : Screen(route = Destinations.DETAILS.name)
}