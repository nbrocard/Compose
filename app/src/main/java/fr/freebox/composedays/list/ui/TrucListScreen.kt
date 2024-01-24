package fr.freebox.composedays.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.freebox.composedays.common.model.MainGraph
import fr.freebox.composedays.common.model.navigateToDirection
import fr.freebox.composedays.common.model.route
import fr.freebox.composedays.list.model.TrucDestination
import fr.freebox.composedays.list.model.TrucListArguments
import fr.freebox.composedays.list.viewmodel.ListViewModel

fun NavGraphBuilder.trucListScreen(navController: NavController) {
    composable(
        TrucDestination.route(),
        arguments = listOf(navArgument(TrucListArguments::numberOfTrucs.name) { type = NavType.IntType })
    ) {
        TrucListScreen {
            when (it) {
                is MainGraph.TrucListDirection.Home -> navController.navigateToDirection(it)
                is MainGraph.TrucListDirection.TrucList -> navController.navigateToDirection(it)
            }
        }
    }
}

@Composable
fun TrucListScreen(viewModel: ListViewModel = viewModel(), onNavigate: (MainGraph.TrucListDirection<*>) -> Unit = {}) {
    val loadingState by viewModel.loadingState.collectAsState()
    val title by viewModel.title.observeAsState("")

    TrucListScreenContent(title = title, loadingState = loadingState, viewModel::onReloadButtonClicked) {
        onNavigate(MainGraph.TrucListDirection.TrucList(TrucListArguments(5, it.subtitle)))
    }
}
