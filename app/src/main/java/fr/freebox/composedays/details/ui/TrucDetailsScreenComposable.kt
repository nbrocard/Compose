package fr.freebox.composedays.details.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.freebox.composedays.common.ui.SimpleBaseScreen
import fr.freebox.composedays.data.TrucDataSource
import fr.freebox.composedays.list.model.Truc
import fr.freebox.composedays.ui.component.FbxPrimaryButton

@Composable
fun TrucDetailsContent(truc: Truc) {
    var hidden by remember { mutableStateOf(false) }

    SimpleBaseScreen(title = "Test") {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(text = truc.name, fontSize = 24.sp)
            Text(text = truc.subtitle)

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(visible = !hidden) {
                Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
                    Surface(border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface)) {
                        Image(painter = painterResource(id = truc.image), contentDescription = truc.name)
                    }
                }
            }

            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                val text = if (hidden) "Show" else "Hide"
                FbxPrimaryButton(text = text) {
                    hidden = !hidden
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TrucDetailsPreview() {
    TrucDetailsContent(truc = TrucDataSource.getTruc(0))
}