package ru.todo.present.ui.main

import ru.todo.present.core.model.Work
import ru.todo.present.mvi.MviResult

sealed class MainResult : MviResult {
    class Downloaded(val works: List<Work>) : MainResult()
    class UpdateItem(val item: Work) : MainResult()
    object Progress : MainResult()
}