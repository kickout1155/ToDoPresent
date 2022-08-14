package ru.todo.present.core.datasource

import ru.todo.present.core.model.Work
import ru.todo.present.mvi.Response

interface NetworkDataSource {

    suspend fun getWorks(): Response<List<Work>>
}