package org.wahid.instabugweatherapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.WeatherUnits
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    lateinit var domainWeatherDay: DomainWeatherDay
    lateinit var fiveDaysForecast: List<DomainWeatherDay>
    val latch = CountDownLatch(1)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.wahid.instabugweatherapp", appContext.packageName)
    }

    @Test
    fun get_current_weather_use_case_assert_DomainWeatherDay_object() {
        AppContainer.weatherRepositoryImpl.getCurrentWeather(
            lat = 37.421998333333335,
            lon = -122.084,
            unit = WeatherUnits.metric,
            callback = object : Callback<DomainWeatherDay> {


                override fun onSuccess(result: DomainWeatherDay) {
                    domainWeatherDay = result
                    latch.countDown()
                }

                override fun onError(error: Throwable) {

                }
            }

        )

        latch.await(5, TimeUnit.SECONDS)
        assertEquals(
            DomainWeatherDay(
                date = "2025-05-09",
                temp = 19.7,
                maxTemp = 28.1,
                minTemp = 12.0,
                humidity = 54.0,
                precip = 54.0,
                windSpeed = 54.0,
                pressure = 54.0,
                description = "Clear conditions throughout the day.",
                lastUpdate = 1746834602914,
                timezone = "America/Los_Angeles",
                address = "Los_Angeles"
            ),
            domainWeatherDay
        )
    }

    @Test
    fun get_five_forecast_days_weather_useCase_asser_listOf_DomainWeatherDay() {


        AppContainer.weatherRepositoryImpl.getFiveDayForecast(
            unit = WeatherUnits.metric,
            callback = object : Callback<List<DomainWeatherDay>> {
                override fun onSuccess(result: List<DomainWeatherDay>) {
                    fiveDaysForecast = result
                    latch.countDown()
                }

                override fun onError(error: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )

        latch.await(5, TimeUnit.SECONDS)


        assertEquals(
            buildList(5) {
                for (i in 0..4) {
                    add(
                        i,
                        DomainWeatherDay(
                            date        = "2025-05-0${i+9}",
                            temp        = 19.7,
                            maxTemp     = 28.1,
                            minTemp     = 12.0,
                            humidity    = 54.0,
                            precip      = 54.0,
                            windSpeed   = 54.0,
                            pressure    = 54.0,
                            description = "Clear conditions throughout the day.",
                            lastUpdate  = 1746834602914,
                            timezone    = "America/Los_Angeles",
                            address     = "Los_Angeles"
                        )
                    )
                }
            },
            fiveDaysForecast
        )
    }


}