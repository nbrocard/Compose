package fr.freebox.composedays.list.model

import androidx.lifecycle.SavedStateHandle
import fr.freebox.composedays.common.model.Arguments
import fr.freebox.composedays.common.model.Path

data class TrucListArguments(@Path val numberOfTrucs: Int) : Arguments {
    constructor(savedStateHandle: SavedStateHandle) : this(
        (checkNotNull(savedStateHandle["numberOfTrucs"]) as String).toInt()
    )
}