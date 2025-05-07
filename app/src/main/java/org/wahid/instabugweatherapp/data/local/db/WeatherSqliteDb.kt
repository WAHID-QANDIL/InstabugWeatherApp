package org.wahid.instabugweatherapp.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.wahid.instabugweatherapp.data.local.db.dao.WeatherDao
import org.wahid.instabugweatherapp.utils.AppContainer

class WeatherSqliteDb(context: Context) :
    SQLiteOpenHelper(context, databaseName, null, databaseVersion) {
    companion object {
        val databaseName = "wather.db"
        val databaseVersion = 1

        const val TABLE_NAME        = "weather_table"
        const val COL_DATE          = "date"
        const val COL_TEMP          = "temp"
        const val COL_TEMPMAX       = "temp_max"
        const val COL_TEMPMIN       = "temp_min"
        const val COL_HUMID         = "humidity"
        const val COL_PRECIP        = "precip"
        const val COL_WIND          = "wind_speed"
        const val COL_PRESS         = "pressure"
        const val COL_ADDRESS       = "address"
        const val COL_DESC          = "description"
        const val COL_LAST_UPDATE   = "last_update"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
                CREATE TABLE $TABLE_NAME(
                $COL_DATE           TEXT PRIMARY KEY,
                $COL_TEMP           REAL,
                $COL_TEMPMAX        REAL,
                $COL_TEMPMIN        REAL,
                $COL_HUMID          REAL,
                $COL_PRECIP         REAL,
                $COL_WIND           REAL,
                $COL_PRESS          REAL,
                $COL_ADDRESS        TEXT,
                $COL_DESC           TEXT,
                $COL_LAST_UPDATE    REAL
                )
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int,
    ) {
        db?.execSQL(
            """DROP TABLE IF EXISTS $TABLE_NAME"""
        )
        onCreate(db)
    }



    fun getDao() = WeatherDao(AppContainer.getDatabase())
}