package ru.todo.present.ui.main

import ru.todo.present.mvi.MviIntent
import ru.todo.present.ui.viewobject.WorkVo

sealed class MainIntent : MviIntent {
    class ClickItem(val item: WorkVo) : MainIntent()
}