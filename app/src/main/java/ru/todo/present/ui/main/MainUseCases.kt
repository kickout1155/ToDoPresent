package ru.todo.present.ui.main

import ru.todo.present.core.usecase.GetWorksUseCase
import javax.inject.Inject

class MainUseCases @Inject constructor(
    private val getWorksUseCase: GetWorksUseCase
) {
    suspend fun getWorks(){
        return getWorksUseCase.execute()
    }
}