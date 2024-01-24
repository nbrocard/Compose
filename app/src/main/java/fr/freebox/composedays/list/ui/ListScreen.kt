package fr.freebox.composedays.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.freebox.composedays.common.model.LoadingState
import fr.freebox.composedays.common.model.MainGraph
import fr.freebox.composedays.common.ui.SimpleBaseScreen
import fr.freebox.composedays.list.model.Truc
import fr.freebox.composedays.list.viewmodel.ListViewModel
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.theme.ComposeDaysTheme

@Composable
fun ListScreen(viewModel: ListViewModel = viewModel(), onNavigate: (MainGraph) -> Unit = {}) {
    val trucs by viewModel.trucs.observeAsState(emptyList())
    val loadingState by viewModel.loadingState.collectAsState()

    ListScreenContent(trucs = trucs, loadingState = loadingState, viewModel::onReloadButtonClicked)
}

@Composable
private fun ListScreenContent(
    trucs: List<Truc>,
    loadingState: LoadingState,
    onReloadButtonClicked: () -> Unit = {}
) {
    SimpleBaseScreen("List de trucs", loadingState = loadingState) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column {
                TrucList(trucs = trucs, onReloadButtonClicked)
            }
        }
    }
}

@Composable
private fun TrucList(trucs: List<Truc>, onReloadButtonClicked: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        items(trucs) {
            TrucItem(truc = it)
        }
        item {
            Box(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
            ) {
                FbxPrimaryButton(text = "Reload", onClick = onReloadButtonClicked)
            }
        }
    }
}

private class TrucsProvider : PreviewParameterProvider<List<Truc>> {
    override val values = sequenceOf(
        listOf(
            Truc("Sample data 1", 0, ""),
            Truc("Sample data 2", 1, ""),
            Truc("Sample data 3", 2, ""),
        )
    )
}

@Composable
private fun TrucItem(truc: Truc) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = truc.name, modifier = Modifier)
        Text(text = truc.subtitle)
    }
}

@Preview
@Composable
private fun Preview(@PreviewParameter(TrucsProvider::class) trucs: List<Truc>) {
    ComposeDaysTheme {
        ListScreenContent(trucs, LoadingState.Done)
    }
}

@Preview
@Composable
private fun RunnablePreview() {
    ComposeDaysTheme {
        ListScreen()
    }
}