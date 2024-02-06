package fr.freebox.composedays.common.model

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import fr.freebox.composedays.details.model.TrucDetailsArguments
import fr.freebox.composedays.details.model.TrucDetailsDirection
import fr.freebox.composedays.details.ui.detailsScreen
import fr.freebox.composedays.home.model.HomeDestination
import fr.freebox.composedays.home.ui.homeScreen
import fr.freebox.composedays.list.model.TrucDestination
import fr.freebox.composedays.list.model.TrucListArguments
import fr.freebox.composedays.list.ui.trucListScreen

//Graph
sealed interface MainGraph {
    sealed class HomeDirection<A : Arguments>(override val endDirection: Destination<A>) : MainGraph, Direction<HomeDestination, A> {
        data class TrucList(override val arguments: TrucListArguments) : HomeDirection<TrucListArguments>(TrucDestination) {
            constructor(numberOfTruc: Int, parent: String) : this(TrucListArguments(numberOfTruc, parent))
        }
    }

    sealed class TrucListDirection<A : Arguments>(override val endDirection: Destination<A>) : MainGraph, Direction<TrucDestination, A> {
        data class Home(override val arguments: NoArgument = NoArgument) : TrucListDirection<NoArgument>(HomeDestination)

        data class TrucList(
            override val arguments: TrucListArguments,
            override val popUpToRoute: String = HomeDestination.route()
        ) : TrucListDirection<TrucListArguments>(TrucDestination)

        data class TrucDetails(override val arguments: TrucDetailsArguments) : TrucListDirection<TrucDetailsArguments>(TrucDetailsDirection) {
            constructor(index: Int) : this(TrucDetailsArguments(index))
        }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    homeScreen(navController)
    trucListScreen(navController)
    detailsScreen(navController)
}