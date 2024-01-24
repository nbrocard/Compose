package fr.freebox.composedays.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.freebox.composedays.common.model.MainGraph
import fr.freebox.composedays.common.model.navigateToDirection
import fr.freebox.composedays.common.model.route
import fr.freebox.composedays.home.model.HomeDestination
import fr.freebox.composedays.home.viewmodel.HomeViewModel

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(HomeDestination.route()) {
        val viewModel: HomeViewModel = viewModel()

        HomeScreen(viewModel) {
            when (it) {
                is MainGraph.HomeDirection.TrucList -> navController.navigateToDirection(it)
            }
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigate: (MainGraph.HomeDirection<*>) -> Unit
) {
    val home by viewModel.home.collectAsState()
    HomeScreenContent(home) {
        when (it) {
            HomeButton.INCREMENT -> viewModel.onIncrementButtonClicked()
            HomeButton.DECREMENT -> viewModel.onDecrementButtonClicked()
            HomeButton.TRUC_1 -> onNavigate(MainGraph.HomeDirection.TrucList(3, "petit truc"))
            HomeButton.TRUC2 -> onNavigate(MainGraph.HomeDirection.TrucList(442, "plein de trucs"))
        }
    }
}

enum class HomeButton { INCREMENT, DECREMENT, TRUC_1, TRUC2 }