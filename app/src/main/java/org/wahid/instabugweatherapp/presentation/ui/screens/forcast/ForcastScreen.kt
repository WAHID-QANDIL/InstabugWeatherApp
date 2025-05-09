package org.wahid.instabugweatherapp.presentation.ui.screens.forcast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import org.wahid.instabugweatherapp.data.repository.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.wahid.instabugweatherapp.presentation.ui.screens.core.ErrorCard
import org.wahid.instabugweatherapp.presentation.ui.screens.core.WeatherDayCard

@Composable
fun ForecastScreen(
    forscastScreenVM: ForecastScreenViewModel,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    val uiState by forscastScreenVM.uiState.observeAsState(initial = ForecastUiState.Loading)
    ForcaseScreenContent(
        modifier = modifier,
        uiState = uiState,
        onClickBack = onClickBack,
        onRetry = { forscastScreenVM.onReLoad() })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForcaseScreenContent(
    modifier: Modifier = Modifier,
    uiState: ForecastUiState,
    onClickBack: () -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
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
        val scrollState = rememberLazyListState()

        when (uiState) {
            is ForecastUiState.Loading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading...", color = Color.Red)
                }
            }

            is ForecastUiState.Error -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorCard(message = uiState.message, onRetry = onRetry)
                }
            }

            is ForecastUiState.Success -> {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        items = uiState.result,
                        key = { it.date }
                    ) { day ->
                        WeatherDayCard(day = day)
                    }
                }
            }
        }
    }


}