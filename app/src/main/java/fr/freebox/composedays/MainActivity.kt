package fr.freebox.composedays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.freebox.composedays.common.ui.MainNavigation
import fr.freebox.composedays.ui.theme.ComposeDaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    ComposeDaysTheme {
        MainNavigation()
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    Content()
}