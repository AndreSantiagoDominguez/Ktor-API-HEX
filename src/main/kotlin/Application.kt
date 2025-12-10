import author.infrastructure.dependencies.AuthorDependencies
import author.infrastructure.routes.authorRoutes
import book.infrastructure.dependencies.BookDependencies
import book.infrastructure.routes.bookRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val logger = LoggerFactory.getLogger("Application")
    logger.info("Iniciando servidor en hilo: ${Thread.currentThread().name}")

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    val authorDependencies = AuthorDependencies()
    val bookDependencies = BookDependencies(authorDependencies.repository)

    routing {
        authorRoutes(authorDependencies)
        bookRoutes(bookDependencies)
    }

    logger.info("Servidor listo en http://localhost:8080")
}
