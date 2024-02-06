package fr.freebox.composedays.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.freebox.composedays.data.TrucDataSource
import fr.freebox.composedays.list.model.Truc

@Composable
fun TrucDetailsContent(truc: Truc) {

}

@Preview(showSystemUi = true)
@Composable
private fun TrucDetailsPreview() {
    TrucDetailsContent(truc = TrucDataSource.getTruc(0))
}