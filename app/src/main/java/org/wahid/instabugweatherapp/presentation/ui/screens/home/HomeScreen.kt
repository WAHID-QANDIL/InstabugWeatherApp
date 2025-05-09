package org.wahid.instabugweatherapp.presentation.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import org.wahid.instabugweatherapp.data.repository.R
import org.wahid.instabugweatherapp.presentation.navigation.Screen
import org.wahid.instabugweatherapp.presentation.ui.screens.core.ErrorCard
import org.wahid.instabugweatherapp.presentation.ui.screens.core.WeatherDayCard

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: HomeViewModel,
) {

    val uiState by viewModel.uiState.observeAsState(initial = HomeUiState.Loading)
    Box(modifier = modifier) {
        HomeScreenContent(
            modifier = modifier,
            homeUiState = uiState,
            onClickBack = { navHostController.popBackStack() },
            onRefresh = { viewModel.refresh() },
            onClickShowMore = {
                navHostController.navigate(Screen.Detail.route)
            },
            onStopLocationListening = {
//                Log.d("HomeScreen", "HomeScreen: onStopUpdates triggered")
//                viewModel.onStopUpdates()
            }
        )
    }


}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    onClickBack: () -> Unit,
    onClickShowMore: () -> Unit,
    onRefresh: () -> Unit,
    onStopLocationListening: () -> Unit,
) {

    val isRefreshing = homeUiState is HomeUiState.Loading || homeUiState is HomeUiState.Error
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            onRefresh()
        },

        )
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.instabug_weather)) },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.back
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                )
            )
        },

        ) {
        val pd = it
        Box(
            modifier = Modifier.pullRefresh(
                state = pullToRefreshState,
                enabled = (homeUiState is HomeUiState.Success || homeUiState is HomeUiState.Error)
            )
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .systemBarsPadding(),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                stickyHeader(key = 1) {
                    PullRefreshIndicator(
                        refreshing = isRefreshing,
                        state = pullToRefreshState,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .statusBarsPadding(),
                        contentColor = Color.Blue
                    )
                }

                when (homeUiState) {
                    is HomeUiState.Loading -> {
                        item(key = 2) {
                            Box(
                                modifier = modifier.padding(pd),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Loading...", color = Color.Red)
                            }
                        }

                    }

                    is HomeUiState.Success -> {
                        item(key = 3) {
                            Column(
                                modifier = Modifier.padding(pd),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                WeatherDayCard(
                                    homeUiState.currentWeather
                                )
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Blue
                                    ),
                                    onClick = { onClickShowMore() }) {
                                    Text("Show more days")
                                }
                            }
                            onStopLocationListening()
                        }
                    }

                    is HomeUiState.Error -> {
                        item(key = 4) {
                            Box(
                                modifier = modifier.padding(pd),
                                contentAlignment = Alignment.Center
                            ) {
                                ErrorCard(message = homeUiState.message, onRetry = onRefresh)
                            }
                        }

                    }


                }


            }
        }
    }
}