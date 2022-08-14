package ru.todo.present.mvi

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class MviFragment<
        INTENT : MviIntent,
        VIEW_STATE : MviViewState,
        VIEWMODEL : MviViewModel<INTENT, VIEW_STATE, *, *, *>,
        >(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    protected abstract val viewModel: VIEWMODEL

    protected var _binding: ViewBinding? = null
    protected abstract val binding: ViewBinding

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.viewEvents
                .flowWithLifecycle(lifecycle, STARTED)
                .onEach(::handleViewEvent)
                .launchIn(this)

            viewModel.viewState
                .flowWithLifecycle(lifecycle, STARTED)
                .onEach(::render)
                .launchIn(this)
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewListeners(
            bindClicks = { viewToBind, getIntent ->
                viewToBind.setOnClickListener {
                    viewModel.handleIntent(getIntent())
                }
            }
        )
    }

    protected open fun handleViewEvent(viewEvent: MviViewEvent) {}

    protected abstract fun bindViewListeners(bindClicks: (view: View, getIntent: () -> INTENT) -> Unit)

    protected abstract fun render(viewState: VIEW_STATE)
}
