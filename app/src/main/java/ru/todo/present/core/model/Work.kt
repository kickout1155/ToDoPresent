package ru.todo.present.core.model

data class Work(
    val isReady: Boolean,
    val date: String,
    val important: Important,
    val title:String,
)

enum class Important {
    LOW, BASIC, HIGH
}
