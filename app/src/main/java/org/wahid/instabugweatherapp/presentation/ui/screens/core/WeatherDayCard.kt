package org.wahid.instabugweatherapp.presentation.ui.screens.core

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.wahid.instabugweatherapp.data.repository.R
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDayCard(
    day: DomainWeatherDay,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500
                )
            ),
        onClick = {
            expanded = !expanded
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = day.date,
                    style = MaterialTheme.typography.titleMedium
                )
                    Text(
                        text = day.address,
                        style = MaterialTheme.typography.displaySmall
                    )
            }
            Text(
                text = "Timezone: ${day.timezone}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${day.temp}°",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "H: ${day.maxTemp}°",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "L: ${day.minTemp}°",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = day.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pressure: ${day.pressure} hPa",
                    style = MaterialTheme.typography.bodySmall
                )


            }
            if (expanded) {

                Column {

                    Text(
                        text = "WindSpeed: ${day.windSpeed}",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "Precip: ${day.precip} ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Humidity: ${day.humidity} ",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Text(
                text = if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.End)
            )
        }

    }
}


@Preview
@Composable
private fun WeatherDayCardPreview() {
    WeatherDayCard(
        day = DomainWeatherDay(
            date = "2025-5-8",
            temp = 17.5,
            maxTemp = 34.0,
            minTemp = 15.0,
            description = "This is a weather card preview",
            timezone = "africa/egypt",
            pressure = 55.4,
            address = "Egypt",
            humidity = 4.3,
            precip = 4.4,
            windSpeed = 33.4,
            lastUpdate = 2025,
        )
    )
}