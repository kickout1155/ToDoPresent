package ru.todo.present.ui.main

import ru.todo.present.core.model.Work
import ru.todo.present.mvi.MviAction

sealed class MainAction : MviAction {
    object Init : MainAction()
    class UpdateItem(val item: Work) : MainAction()
}