package fr.freebox.composedays.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.freebox.composedays.data.TrucDataSource
import fr.freebox.composedays.details.model.TrucDetailsArguments
import fr.freebox.composedays.list.model.Truc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TrucDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val index = requireNotNull(savedStateHandle.get<Int>(TrucDetailsArguments::index.name))

    private val _truc = MutableStateFlow(TrucDataSource.getTruc(index))
    val truc: StateFlow<Truc> = _truc
}