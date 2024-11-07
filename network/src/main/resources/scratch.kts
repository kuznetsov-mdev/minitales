import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import kotlinx.coroutines.runBlocking
import ru.sandbox.minitales.network.MiniTalesHttpClientBuilder

val httpClient = MiniTalesHttpClientBuilder()
    .protocol(URLProtocol.HTTPS)
    .host("swapi.dev")
    .build()

runBlocking {
    val result = httpClient.get("/api/people/1").body<String>()
    println(result)
}
