package author.infrastructure.dependencies

import author.application.CreateAuthorUseCase
import author.application.GetAllAuthorsUseCase
import author.application.GetAuthorByIdUseCase
import author.application.repositories.IAuthorRepository
import author.infrastructure.adapters.AuthorInMemoryAdapter
import author.infrastructure.controllers.CreateAuthorController
import author.infrastructure.controllers.GetAllAuthorsController
import author.infrastructure.controllers.GetAuthorByIdController

class AuthorDependencies {

    val repository: IAuthorRepository = AuthorInMemoryAdapter()

    private val getAllAuthorsUseCase = GetAllAuthorsUseCase(repository)
    private val getAuthorByIdUseCase = GetAuthorByIdUseCase(repository)
    private val createAuthorUseCase = CreateAuthorUseCase(repository)

    val getAllAuthorsController = GetAllAuthorsController(getAllAuthorsUseCase)
    val getAuthorByIdController = GetAuthorByIdController(getAuthorByIdUseCase)
    val createAuthorController = CreateAuthorController(createAuthorUseCase)
}
