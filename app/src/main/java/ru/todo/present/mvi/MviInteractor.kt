package ru.todo.present.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class MviInteractor<ACTION : MviAction, RESULT : MviResult> {

    private val _results = MutableSharedFlow<RESULT>()
    val results: Flow<RESULT> = _results

    protected suspend fun postResult(result: RESULT) {
        _results.emit(result)
    }

    abstract suspend fun handleAction(action: ACTION)
}
