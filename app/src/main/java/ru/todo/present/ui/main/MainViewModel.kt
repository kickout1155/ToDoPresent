package ru.todo.present.ui.main

import ru.todo.present.mvi.MviViewModel
import ru.todo.present.ui.formatters.WorkFormatter
import ru.todo.present.ui.viewobject.toDomain

class MainViewModel(
    interactor: MainInteractor,
    private val formatter: WorkFormatter,
//    useCases: MainUseCases,
//    args:MainArgs
) : MviViewModel<
        MainIntent,
        MainViewState,
        MainAction,
        MainResult,
        MainInteractor>(
    initialViewState = MainViewState(
        isLoading = true,
        worksVo = emptyList(),
        countIsReady = 0
    ),
    interactor = interactor
) {
    init {
        launchCoroutine {
            interactor.handleAction(MainAction.Init)
        }
    }

    override fun getActionFromIntent(intent: MainIntent): MainAction {
        return when (intent) {
            is MainIntent.ClickItem -> MainAction.UpdateItem(intent.item.toDomain())
        }
    }

    override suspend fun getViewStateFromResult(
        result: MainResult,
        lastViewState: MainViewState,
    ): MainViewState {
        return when (result) {
            is MainResult.Downloaded -> {
                lastViewState.copy(
                    isLoading = false,
                    worksVo = result.works.map { work -> formatter.format(work) }
                )
            }
            is MainResult.UpdateItem -> {
                val newList = lastViewState.worksVo.toMutableList()
                newList.add(formatter.format(result.item))
                return lastViewState.copy(
                    isLoading = false,
                    worksVo = newList
                )
            }
            MainResult.Progress -> {
                lastViewState.copy(
                    isLoading = true
                )
            }
        }
    }
}