package fr.freebox.composedays.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import fr.freebox.composedays.common.ui.BaseScreen
import fr.freebox.composedays.home.mapper.HomeToTopBarUi
import fr.freebox.composedays.home.mapper.HomeToUi
import fr.freebox.composedays.home.model.Home
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.component.FbxSecondaryButton

@Composable
fun HomeScreenContent(home: Home, onButtonClicked: (HomeButton) -> Unit = {}) {
    BaseScreen(topBar = HomeToTopBarUi()(home)) {
        Column {
            FbxPrimaryButton(text = "Increment", onClick = { onButtonClicked(HomeButton.INCREMENT) })
            with(HomeToUi()(home)) {
                if (showDecrementButton) {
                    FbxSecondaryButton(text = "Décrement", onClick = { onButtonClicked(HomeButton.DECREMENT) })
                }
            }
            FbxPrimaryButton(text = "Go to 3 truc", onClick = { onButtonClicked(HomeButton.TRUC_1) })
            FbxPrimaryButton(text = "Go to 442 truc", onClick = { onButtonClicked(HomeButton.TRUC2) })
        }
    }
}