package fr.freebox.composedays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ContentView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fr.freebox.composedays.ui.component.FbxLinkButton
import fr.freebox.composedays.ui.component.FbxPrimaryButton
import fr.freebox.composedays.ui.component.FbxSecondaryButton
import fr.freebox.composedays.ui.theme.ComposeDaysTheme
import fr.freebox.composedays.ui.theme.ExtendedTheme

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
        Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
            Surface(modifier = Modifier.padding(contentPadding)) {
                Column {
                    FbxPrimaryButton(modifier = modifier, text = "primary")
                    FbxSecondaryButton(modifier = modifier, text = "secondary")
                    FbxLinkButton(modifier = modifier, text = "Lien vers un truc cool")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    Content(Modifier.fillMaxWidth())
}