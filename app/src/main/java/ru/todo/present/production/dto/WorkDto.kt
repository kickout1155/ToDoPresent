package ru.todo.present.production.dto

import ru.todo.present.core.model.Important

class WorkDto(
    val isReady: Boolean,
    val date: String,
    val title: String,
)