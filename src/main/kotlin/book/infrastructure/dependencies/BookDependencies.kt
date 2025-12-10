package book.infrastructure.dependencies

import author.application.repositories.IAuthorRepository
import book.application.CreateBookUseCase
import book.application.GetAllBooksUseCase
import book.application.GetAllBooksWithAuthorsUseCase
import book.application.GetBookByIdUseCase
import book.application.repositories.IBookRepository
import book.infrastructure.adapters.BookInMemoryAdapter
import book.infrastructure.controllers.CreateBookController
import book.infrastructure.controllers.GetAllBooksController
import book.infrastructure.controllers.GetAllBooksWithAuthorsController
import book.infrastructure.controllers.GetBookByIdController

class BookDependencies(authorRepository: IAuthorRepository) {

    private val repository: IBookRepository = BookInMemoryAdapter()

    private val getAllBooksUseCase = GetAllBooksUseCase(repository)
    private val getBookByIdUseCase = GetBookByIdUseCase(repository)
    private val createBookUseCase = CreateBookUseCase(repository)
    private val getAllBooksWithAuthorsUseCase = GetAllBooksWithAuthorsUseCase(repository, authorRepository)

    val getAllBooksController = GetAllBooksController(getAllBooksUseCase)
    val getBookByIdController = GetBookByIdController(getBookByIdUseCase)
    val createBookController = CreateBookController(createBookUseCase)
    val getAllBooksWithAuthorsController = GetAllBooksWithAuthorsController(getAllBooksWithAuthorsUseCase)
}
