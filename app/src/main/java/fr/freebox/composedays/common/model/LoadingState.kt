package fr.freebox.composedays.common.model

sealed interface LoadingState {
    data object Loading : LoadingState
    data object Done : LoadingState
}