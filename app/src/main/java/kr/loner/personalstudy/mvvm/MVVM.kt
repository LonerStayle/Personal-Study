package kr.loner.personalstudy.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


fun main() {
    val repository = MvvmRepository()
    val viewModel = ViewModel(repository)
    val view = MvvmView(viewModel)
    view.observeData()

    /**View 가 필요 없을 때*/
    view.cancelJob()
}

private class MvvmRepository {
    fun getData() = "getData"
}

private class ViewModel(private val repository: MvvmRepository) {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        setData()
    }

    private fun setData() {
        _uiState.value = uiState.value.copy(data = repository.getData())
    }

    data class UiState(
        val data: String = ""
    )
}

private class MvvmView(private val viewModel: ViewModel) {
    val job = CoroutineScope(Dispatchers.Main)

    /* DataBinding 라이브러리를 사용하지 않으므로 옵저버 패턴 사용*/
    fun observeData() {
        job.launch {
            viewModel.uiState.collectLatest { state ->
                println(state.data)
            }
        }
    }

    fun cancelJob() {
        job.cancel()
    }
}


