package org.wahid.instabugweatherapp.data.custom_exceptions

import org.wahid.instabugweatherapp.data.repository.R
import java.io.IOException

sealed class WeatherRemoteCustomException(message: String? = null) : IOException(message){
    class AuthenticationException :
        WeatherRemoteCustomException(R.string.error_unauthorized.toString())

    class NotFoundException :
        WeatherRemoteCustomException(R.string.error_not_found.toString())

    class ServerErrorException :
        WeatherRemoteCustomException(R.string.error_server.toString())

    class NetworkException :
        WeatherRemoteCustomException(R.string.error_network.toString())
}
