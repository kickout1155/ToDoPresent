package ru.todo.present.ui.main

import ru.todo.present.mvi.MviViewState
import ru.todo.present.ui.viewobject.WorkVo

data class MainViewState(
    val isLoading: Boolean,
    val worksVo: List<WorkVo>,
    val countIsReady:Int,
) : MviViewState {
}