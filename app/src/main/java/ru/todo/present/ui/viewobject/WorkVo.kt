package ru.todo.present.ui.viewobject

import ru.todo.present.core.model.Important
import ru.todo.present.core.model.Work

data class WorkVo(
    val title: String,
    val isReady: Boolean,
    val date: String,
    val image: Int,
)

fun WorkVo.toDomain(): Work {
    return Work(
        isReady = isReady,
        title = title,
        date = date,
        important = Important.LOW
    )
}