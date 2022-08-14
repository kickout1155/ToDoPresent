package ru.todo.present.ui.formatters

import ru.todo.present.R
import ru.todo.present.core.model.Important
import ru.todo.present.core.model.Work
import ru.todo.present.ui.viewobject.WorkVo
import javax.inject.Inject

class WorkFormatter @Inject constructor() {

    fun format(work:Work):WorkVo{
        return WorkVo(
            isReady = work.isReady,
            date = work.date,
            title = work.title,
            image = when(work.important){
                Important.LOW-> R.drawable.ic_baseline_arrow_downward
                Important.BASIC-> R.drawable.ic_baseline_arrow_downward
                Important.HIGH-> R.drawable.ic_baseline_priority_high
            }
        )
    }
}