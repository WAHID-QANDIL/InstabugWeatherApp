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
                delete(WeatherSqliteDb.TABLE_NAME, null, null)
                days.forEach { day ->
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
                        put(WeatherSqliteDb.COL_ADDRESS,        day.address)
                    }
                    insert(WeatherSqliteDb.TABLE_NAME, null, cv)
                }
            }catch (e: Exception){
                throw e
            }

        }
    }

    fun load(): List<WeatherDbEntity> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            WeatherSqliteDb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${WeatherSqliteDb.COL_DATE} ASC"
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
                    precip          = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                    windSpeed       = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                    pressure        = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                    address         = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_ADDRESS)),
                    description     = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_DESC)),
                    lastUpdate      = it.getLong(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_LAST_UPDATE))
                )
            }

        }
        return list

    }
    fun getFirst(): WeatherDbEntity{
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            WeatherSqliteDb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${WeatherSqliteDb.COL_DATE} ASC"
        )
        var item: WeatherDbEntity? = null
        cursor.use {
                item = WeatherDbEntity(
                date            = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_DATE)),
                temp            = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMP)),
                tempMax         = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMAX)),
                tempMin         = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_TEMPMIN)),
                humidity        = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                precip          = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                windSpeed       = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                pressure        = it.getDouble(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_HUMID)),
                address         = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_ADDRESS)),
                description     = it.getString(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_DESC)),
                lastUpdate      = it.getLong(it.getColumnIndexOrThrow(WeatherSqliteDb.COL_LAST_UPDATE))
            )
        }
        return item!!

    }


}