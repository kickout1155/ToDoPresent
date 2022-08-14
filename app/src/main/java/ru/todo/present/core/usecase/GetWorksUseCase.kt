package ru.todo.present.core.usecase

import ru.todo.present.core.repository.WorkRepository
import javax.inject.Inject

class GetWorksUseCase @Inject constructor(
    private val repository: WorkRepository,
) {
    suspend fun execute() = repository.getWorks()
}