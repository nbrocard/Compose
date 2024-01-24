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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import fr.freebox.composedays.common.model.LoadingState
import fr.freebox.composedays.common.ui.SimpleLoadingBaseScreen
import fr.freebox.composedays.list.model.Truc
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.theme.ComposeDaysTheme


@Composable
fun TrucListScreenContent(
    title: String,
    loadingState: LoadingState<List<Truc>>,
    onReloadButtonClicked: () -> Unit = {},
    onItemClick: (Truc) -> Unit = {}
) {
    SimpleLoadingBaseScreen("Liste de $title", loadingState = loadingState) { trucs ->
        Column(modifier = Modifier.fillMaxSize()) {
            TrucList(trucs = trucs, onReloadButtonClicked, onItemClick)
        }
    }
}

@Composable
private fun TrucList(trucs: List<Truc>, onReloadButtonClicked: () -> Unit, onItemClick: (Truc) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize()
    ) {
        items(trucs) {
            TrucItem(truc = it) { onItemClick(it) }
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


@Composable
private fun TrucItem(truc: Truc, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            Text(text = truc.name, modifier = Modifier)
            Text(text = "(${truc.subtitle})")
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

@Preview
@Composable
private fun Preview(@PreviewParameter(TrucsProvider::class) trucs: List<Truc>) {
    ComposeDaysTheme {
        TrucListScreenContent("Trucs", LoadingState.Done(trucs))
    }
}