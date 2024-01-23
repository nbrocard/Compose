package fr.freebox.composedays.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.freebox.composedays.common.model.LoadingState
import fr.freebox.composedays.list.model.Truc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val _trucs = MutableLiveData<List<Truc>>()
    val trucs: LiveData<List<Truc>> = _trucs

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

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
                listOf(
                    Truc("truc trop cool", 0, "lol"),
                    Truc("truc moins cool", 1, "lol"),
                    Truc("truc de merde", 2, "lol"),
                )
            )
        }
    }

    private fun onData(trucs: List<Truc>) {
        _trucs.value = trucs
        setDone()
    }

    private fun setLoading() {
        _loadingState.value = LoadingState.Loading
    }

    private fun setDone() {
        _loadingState.value = LoadingState.Done
    }
}