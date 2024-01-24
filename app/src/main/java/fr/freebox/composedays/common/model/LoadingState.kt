package fr.freebox.composedays.common.model

sealed interface LoadingState<T> {
    class Loading<T> : LoadingState<T>
    data class Done<T>(val data: T) : LoadingState<T>
}