package ru.todo.present.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.todo.present.ui.formatters.WorkFormatter
import javax.inject.Inject

class MainViewModelFactory(
    private val formatter: WorkFormatter,
    private val interactor: MainInteractor,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            interactor = interactor,
            formatter = formatter
        ) as T
    }

    class Factory @Inject constructor(
        private val interactor: MainInteractor,
        private val formatter: WorkFormatter,
    ) {
        fun create(): MainViewModelFactory {
            return MainViewModelFactory(
                interactor = interactor,
                formatter = formatter,
            )
        }
    }
}