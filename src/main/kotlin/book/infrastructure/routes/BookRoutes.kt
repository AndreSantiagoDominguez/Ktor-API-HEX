package book.infrastructure.routes

import book.domain.Book
import book.infrastructure.dependencies.BookDependencies
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRoutes(dependencies: BookDependencies) {

    route("/books") {

        get {
            val books = dependencies.getAllBooksController.handle()
            call.respond(books)
        }

        get("/{id}") {
            val id = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.BadRequest, mapOf("error" to "ID requerido"))

            val book = dependencies.getBookByIdController.handle(id)
            if (book != null) {
                call.respond(book)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Libro no encontrado"))
            }
        }

        post {
            val book = call.receive<Book>()
            val created = dependencies.createBookController.handle(book)
            call.respond(HttpStatusCode.Created, created)
        }

        get("/with-authors") {
            val booksWithAuthors = dependencies.getAllBooksWithAuthorsController.handle()
            call.respond(booksWithAuthors)
        }
    }
}
