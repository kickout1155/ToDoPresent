package ru.todo.present.core.repository

import ru.todo.present.core.datasource.NetworkDataSource
import javax.inject.Inject

class WorkRepository @Inject constructor(
    private val dataSource: NetworkDataSource
) {
    suspend fun getWorks() = dataSource.getWorks()
}