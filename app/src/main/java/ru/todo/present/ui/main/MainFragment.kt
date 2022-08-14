package ru.todo.present.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.todo.present.R
import ru.todo.present.databinding.MainFragmentBinding
import ru.todo.present.mvi.MviFragment
import ru.todo.present.ui.adapters.WorkAdapter
import ru.todo.present.ui.viewobject.WorkVo
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : MviFragment<
        MainIntent,
        MainViewState,
        MainViewModel>(R.layout.main_fragment) {

    @Inject
    lateinit var factory: MainViewModelFactory.Factory

    override val viewModel: MainViewModel by viewModels {
        factory.create()
    }

    private val workAdapter = WorkAdapter(
        clickInfo = { vo -> clickItem(vo) }
    )

    override val binding: MainFragmentBinding get() = checkNotNull(_binding) as MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecycler()
    }

    private fun bindRecycler() {
        val verticalLM = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.apply {
            layoutManager = verticalLM
            adapter = workAdapter
        }
    }

    override fun bindViewListeners(bindClicks: (view: View, getIntent: () -> MainIntent) -> Unit) {

    }

    override fun render(viewState: MainViewState) {
        binding.apply {
            progressBar.isVisible = viewState.isLoading
            mainContainer.isVisible = !viewState.isLoading
            workAdapter.submitList(viewState.worksVo)
        }
    }


    private fun clickItem(vo: WorkVo) {
        viewModel.handleIntent(MainIntent.ClickItem(vo))
    }
}