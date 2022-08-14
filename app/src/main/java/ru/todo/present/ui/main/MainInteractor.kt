package ru.todo.present.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import ru.todo.present.core.model.Important
import ru.todo.present.core.model.Work
import ru.todo.present.core.usecase.GetWorksUseCase
import ru.todo.present.mvi.MviInteractor
import ru.todo.present.mvi.Response
import javax.annotation.meta.When
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val getWorksUseCase: GetWorksUseCase,
) : MviInteractor<MainAction, MainResult>() {

    override suspend fun handleAction(action: MainAction) {
        when (action) {
            MainAction.Init -> {
                val works = withContext(Dispatchers.IO) {
                    delay(3000)
                    getWorksUseCase.execute()
                }
                when (works) {
                    is Response.Success -> postResult(MainResult.Downloaded(works.item))
                }

            }
            is MainAction.UpdateItem -> {
                postResult(MainResult.Progress)
                delay(2000)
                //updateusecase
                postResult(MainResult.UpdateItem(Work(
                    true,
                    "09.08.2020",
                    Important.LOW,
                    "update")))
            }
        }
    }
}
