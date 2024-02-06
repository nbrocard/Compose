package fr.freebox.composedays.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import fr.freebox.composedays.common.model.composable
import fr.freebox.composedays.details.model.TrucDetailsDirection
import fr.freebox.composedays.details.viewmodel.TrucDetailsViewModel


fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(TrucDetailsDirection) {
        val viewModel: TrucDetailsViewModel = viewModel()

        TrucDetailsScreen(viewModel = viewModel)
    }
}

@Composable
fun TrucDetailsScreen(viewModel: TrucDetailsViewModel) {
    val truc by viewModel.truc.collectAsState()

    TrucDetailsContent(truc = truc)
}