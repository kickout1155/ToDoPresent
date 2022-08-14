package ru.todo.present.mvi

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviViewModel<
        INTENT : MviIntent,
        VIEW_STATE : MviViewState,
        ACTION : MviAction,
        RESULT : MviResult,
        INTERACTOR : MviInteractor<ACTION, RESULT>,
        >(
    initialViewState: VIEW_STATE,
    private val interactor: INTERACTOR,
) : ViewModel() {

    private val _viewEvents = MutableSharedFlow<MviViewEvent>()
    val viewEvents: SharedFlow<MviViewEvent> = _viewEvents

    private val _viewState = MutableStateFlow(initialViewState)
    val viewState: StateFlow<VIEW_STATE> = _viewState

    init {
        viewModelScope.launch {
            interactor.results.collect { result ->
                _viewState.value = getViewStateFromResult(result, viewState.value)
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        e("ViewModel exception", exception.localizedMessage ?: "unknown exception")
    }

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(handler) { block() }

    fun handleIntent(intent: INTENT) {
        viewModelScope.launch {
            interactor.handleAction(
                getActionFromIntent(intent)
            )
        }
    }

    protected suspend fun postViewEvent(viewEvent: MviViewEvent) {
        _viewEvents.emit(viewEvent)
    }

    protected abstract fun getActionFromIntent(intent: INTENT): ACTION

    protected abstract suspend fun getViewStateFromResult(
        result: RESULT,
        lastViewState: VIEW_STATE,
    ): VIEW_STATE
}
