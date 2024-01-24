package fr.freebox.composedays.common.model

import androidx.navigation.NavController

interface Direction<Start : Destination<*>, Args : Arguments> {
    val endDirection: Destination<Args>

    val popUpToRoute: String?
        get() = null
    val popUpInclusive: Boolean
        get() = false

    @Suppress("UNCHECKED_CAST")
    val arguments: Args
        get() = NoArgument as Args
}

inline fun <reified A : Arguments> NavController.navigateToDirection(direction: Direction<*, A>) {
    navigate(direction.endDirection.route(direction.arguments)) {
        direction.popUpToRoute?.let {
            popUpTo(it) {
                inclusive = direction.popUpInclusive
            }
        }
    }
}