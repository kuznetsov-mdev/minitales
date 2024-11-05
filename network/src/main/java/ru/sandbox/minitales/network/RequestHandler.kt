package ru.sandbox.minitales.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestHandler(val httpClient: HttpClient) {

    suspend inline fun <reified B, reified R> executeRequest(
        method: HttpMethod,
        urlPathSegments: List<Any>,
        body: B? = null,
        queryParams: Map<String, Any>? = null
    ): NetworkResult<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegments = urlPathSegments.map { it.toString() }
                        appendPathSegments(pathSegments)
                    }
                    body?.let { setBody(it) }

                    queryParams?.let { params ->
                        params.forEach { (key, value) ->
                            parameter(key, value)
                        }
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            } catch (e: Exception) {
                val networkException = if (e is ResponseException) {
                    val errorBody = e.response.body<DefaultError>()
                    when (e.response.status) {
                        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(
                            errorBody.message,
                            e
                        )

                        else -> NetworkException.NotFoundException("API Not found", e)
                    }
                } else {
                    NetworkException.UnknownException(e.message ?: "Unknown error", e)
                }
                NetworkResult.Error(null, networkException)
            }
        }
    }

    suspend inline fun <reified R> get(
        urlParameters: List<Any>,
        requestParams: Map<String, Any>? = null
    ): NetworkResult<R> = executeRequest<Any?, R>(
        method = HttpMethod.Get,
        urlPathSegments = urlParameters.toList(),
        queryParams = requestParams
    )

    suspend inline fun <reified B, reified R> post(
        urlParameters: List<Any>,
        body: B? = null
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Post,
        urlPathSegments = urlParameters.toList(),
        body = body
    )

}