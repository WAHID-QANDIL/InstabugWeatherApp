package org.wahid.instabugweatherapp.data.custom_exceptions

import java.io.IOException

sealed class WeatherRemoteCustomException() : IOException(){
    class AuthenticationException :
        WeatherRemoteCustomException()

    class NotFoundException :
        WeatherRemoteCustomException()

    class ServerErrorException :
        WeatherRemoteCustomException()

    class NetworkException :
        WeatherRemoteCustomException()
}
