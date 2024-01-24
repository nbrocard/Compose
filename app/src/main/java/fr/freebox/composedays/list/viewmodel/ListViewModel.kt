package fr.freebox.composedays.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.freebox.composedays.common.model.LoadingState
import fr.freebox.composedays.list.model.Truc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val count = checkNotNull(savedStateHandle.get<Int>("numberOfTrucs"))
    private val parent = checkNotNull(savedStateHandle.get<String>("parent"))

    private val _loadingState = MutableStateFlow<LoadingState<List<Truc>>>(LoadingState.Loading())
    val loadingState: StateFlow<LoadingState<List<Truc>>> = _loadingState.asStateFlow()

    private val _title = MutableLiveData(parent)
    val title: LiveData<String> = _title

    init {
        loadData()
    }

    fun onReloadButtonClicked() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            setLoading()
            delay(2000)
            onData(
                (0..<count).map {
                    Truc("truc trop cool $it", it, "sous-truc de $it")
                }
            )
        }
    }

    private fun onData(trucs: List<Truc>) {
        setDone(trucs)
    }

    private fun setLoading() {
        _loadingState.value = LoadingState.Loading()
    }

    private fun setDone(trucs: List<Truc>) {
        _loadingState.value = LoadingState.Done(trucs)
    }
}