package fr.freebox.composedays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.freebox.composedays.common.ui.BaseScreen
import fr.freebox.composedays.home.ui.HomeScreen
import fr.freebox.composedays.ui.component.FbxLinkButton
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.component.FbxSecondaryButton
import fr.freebox.composedays.ui.theme.ComposeDaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    ComposeDaysTheme {
        HomeScreen()
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    Content(Modifier.fillMaxWidth())
}