package author.infrastructure.routes

import author.domain.Author
import author.infrastructure.dependencies.AuthorDependencies
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authorRoutes(dependencies: AuthorDependencies) {

    route("/authors") {

        get {
            val authors = dependencies.getAllAuthorsController.handle()
            call.respond(authors)
        }

        get("/{id}") {
            val id = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.BadRequest, mapOf("error" to "ID requerido"))

            val author = dependencies.getAuthorByIdController.handle(id)
            if (author != null) {
                call.respond(author)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Autor no encontrado"))
            }
        }

        post {
            val author = call.receive<Author>()
            val created = dependencies.createAuthorController.handle(author)
            call.respond(HttpStatusCode.Created, created)
        }
    }
}
