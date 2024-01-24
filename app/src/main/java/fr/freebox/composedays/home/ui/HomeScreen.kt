package fr.freebox.composedays.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.freebox.composedays.common.model.MainGraph
import fr.freebox.composedays.common.ui.BaseScreen
import fr.freebox.composedays.home.mapper.HomeToTopBarUi
import fr.freebox.composedays.home.mapper.HomeToUi
import fr.freebox.composedays.home.viewmodel.HomeViewModel
import fr.freebox.composedays.list.model.TrucListArguments
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.component.FbxSecondaryButton

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigate: (MainGraph.Home) -> Unit
) {
    val home by viewModel.home.collectAsState()

    BaseScreen(topBar = HomeToTopBarUi()(home)) {
        Column {
            FbxPrimaryButton(text = "Increment", onClick = viewModel::onIncrementButtonClicked)
            with(HomeToUi()(home)) {
                if (showDecrementButton) {
                    FbxSecondaryButton(text = "Décrement", onClick = viewModel::onDecrementButtonClicked)
                }
            }
            FbxPrimaryButton(text = "Go to 3 truc") {
                onNavigate(MainGraph.Home.TrucList(TrucListArguments(3)))
            }
            FbxPrimaryButton(text = "Go to 442 truc") {
                onNavigate(MainGraph.Home.TrucList(TrucListArguments(442)))
            }
        }
    }
}