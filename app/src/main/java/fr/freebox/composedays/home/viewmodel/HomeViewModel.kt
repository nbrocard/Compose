package fr.freebox.composedays.home.viewmodel

import androidx.lifecycle.ViewModel
import fr.freebox.composedays.home.model.Home
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _home = MutableStateFlow(Home())
    val home: StateFlow<Home> = _home.asStateFlow()

    fun onIncrementButtonClicked() {
        _home.update { it.copy(value = it.value + 1) }
    }
    fun onDecrementButtonClicked() {
        _home.update { it.copy(value = it.value - 1) }
    }
}