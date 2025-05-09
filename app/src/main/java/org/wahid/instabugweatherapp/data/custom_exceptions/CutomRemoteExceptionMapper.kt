package org.wahid.instabugweatherapp.data.custom_exceptions

import androidx.annotation.StringRes
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.AuthenticationException
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.NotFoundException
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.ServerErrorException
import org.wahid.instabugweatherapp.data.repository.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorMapper {
    @StringRes
    fun mapToMessageRes(e: Throwable): Int = when (e) {
        is AuthenticationException -> R.string.error_unauthorized
        is NotFoundException       -> R.string.error_not_found
        is ServerErrorException    -> R.string.error_server
        is SocketTimeoutException  -> R.string.error_timeout
        is UnknownHostException    -> R.string.error_network
        else                       -> R.string.error_unknown
    }
}