package org.wahid.instabugweatherapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.wahid.instabugweatherapp.presentation.ui.screens.forcast.ForecastScreen
import org.wahid.instabugweatherapp.presentation.ui.screens.forcast.ForecastScreenViewModel
import org.wahid.instabugweatherapp.presentation.ui.screens.home.HomeScreen
import org.wahid.instabugweatherapp.presentation.ui.screens.home.HomeViewModel

@Composable
fun AppNav(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
) {
    val forcastVm = viewModel<ForecastScreenViewModel>()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {

            HomeScreen(
                viewModel = homeViewModel,
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Screen.Detail.route) {
            ForecastScreen(
                forscastScreenVM = forcastVm,
                modifier = Modifier.fillMaxSize().systemBarsPadding(),
                onClickBack = { navHostController.navigateUp() },
            )
        }

    }
}