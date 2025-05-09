package org.wahid.instabugweatherapp.data.local.db.dao

import android.content.ContentValues
import org.wahid.instabugweatherapp.data.local.db.WeatherSqliteDb
import org.wahid.instabugweatherapp.data.local.db.model.WeatherDbEntity
import androidx.core.database.sqlite.transaction

class WeatherDao(private val dbHelper: WeatherSqliteDb) {

    fun replace(days: List<WeatherDbEntity>) {
        val db = dbHelper.writableDatabase
        db.transaction() {
            try {
            //Cache only five forcast days
                delete(WeatherSqliteDb.TABLE_NAME, null, null)
                days.take(5).forEach { day ->
                    val cv = ContentValues().apply {
                        put(WeatherSqliteDb.COL_DATE,           day.date)
                        put(WeatherSqliteDb.COL_TEMP,           day.temp)
                        put(WeatherSqliteDb.COL_TEMPMAX,        day.tempMax)
                        put(WeatherSqliteDb.COL_TEMPMIN,        day.tempMin)
                        put(WeatherSqliteDb.COL_HUMID,          day.humidity)
                        put(WeatherSqliteDb.COL_PRECIP,         day.precip)
                        put(WeatherSqliteDb.COL_WIND,           day.windSpeed)
                        put(WeatherSqliteDb.COL_PRESS,          day.pressure)
                        put(WeatherSqliteDb.COL_DESC,           day.description)
                        put(WeatherSqliteDb.COL_LAST_UPDATE,    day.lastUpdate)
                        put(WeatherSqliteDb.COL_TIME_ZONE,      day.timeZone)
                    }
                    insert(WeatherSqliteDb.TABLE_NAME, null, cv)
                }
            }catch (e: Exception){
                throw e
            }

        }
    }

    fun loadFiveForcast(): List<WeatherDbEntity> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            WeatherSqliteDb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${WeatherSqliteDb.COL_DATE} ASC","5"
        )
        val list = mutableListOf<WeatherDbEntity>()

        cursor.use {
            while (it.moveToNext()) {
                list += WeatherDbEntity(
                    date            = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_DATE)),
                    temp            = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMP)),
                    tempMax         = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMAX)),
                    tempMin         = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMIN)),
                    humidity        = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                    precip          = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_PRECIP)),
                    windSpeed       = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_WIND)),
                    pressure        = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_PRESS)),
                    timeZone        = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TIME_ZONE)),
                    description     = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_DESC)),
                    lastUpdate      = it.getLong(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_LAST_UPDATE))
                )
            }

        }
        return list

    }
    fun getFirst(): WeatherDbEntity {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            WeatherSqliteDb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${WeatherSqliteDb.COL_DATE} ASC",
            "1"
        )
        cursor.use { c ->

            if (!c.moveToFirst()) {
                throw NoSuchElementException("No weather data available")
            }

            val date        = c.getString(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_DATE))
            val temp        = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMP))
            val tempMax     = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMAX))
            val tempMin     = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMIN))
            val humidity    = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID))
            val precip      = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID))
            val windSpeed   = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID))
            val pressure    = c.getDouble(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID))
            val timeZone     = c.getString(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_TIME_ZONE))
            val description = c.getString(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_DESC))
            val lastUpdate  = c.getLong(c.getColumnIndexOrThrow(WeatherSqliteDb.COL_LAST_UPDATE))

            return WeatherDbEntity(
                date         = date,
                temp         = temp,
                tempMax      = tempMax,
                tempMin      = tempMin,
                humidity     = humidity,
                precip       = precip,
                windSpeed    = windSpeed,
                pressure     = pressure,
//                address      = address,
                timeZone     = timeZone,
                description  = description,
                lastUpdate   = lastUpdate
            )
        }
    }


}