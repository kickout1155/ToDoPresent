package ru.todo.present.core.mapper

import ru.todo.present.core.model.Important
import ru.todo.present.core.model.Work
import ru.todo.present.production.dto.WorkDto
import javax.inject.Inject

class WorkMapper @Inject constructor() {

    fun map(dto: WorkDto): Work {
        return Work(
            isReady = dto.isReady,
            date = dto.date,
            important = if (dto.isReady) {
                Important.HIGH
            } else {
                Important.LOW
            },
            title = dto.title
        )
    }
}