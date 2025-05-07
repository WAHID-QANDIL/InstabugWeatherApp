package org.wahid.instabugweatherapp.utils

import org.wahid.instabugweatherapp.MyApp
import org.wahid.instabugweatherapp.data.local.db.SharedPrefs
import org.wahid.instabugweatherapp.data.local.db.WeatherSqliteDb
import org.wahid.instabugweatherapp.data.local.mediator.Mediator
import org.wahid.instabugweatherapp.data.remote.source.WeatherRemoteApiServiceImpl
import org.wahid.instabugweatherapp.data.repository.WeatherRepositoryImpl

object AppContainer {

    val weatherRemoteApiServiceImpl = WeatherRemoteApiServiceImpl()
    val appContext = MyApp.getAppContext()
    private var databaseInstance: WeatherSqliteDb? = null;

    fun getDatabase(): WeatherSqliteDb {

        if (databaseInstance == null) {
            databaseInstance = WeatherSqliteDb(context = appContext)

        }

        return databaseInstance!!
    }
    val prefs = SharedPrefs(appContext)

    val mediator = Mediator(
        weatherRemoteApiServiceImpl,
        getDatabase(),
        prefs = prefs
    )
    val weatherRepositoryImpl = WeatherRepositoryImpl()

}