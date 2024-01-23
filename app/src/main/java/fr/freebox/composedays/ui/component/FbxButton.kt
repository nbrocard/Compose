package fr.freebox.composedays.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.freebox.composedays.R
import fr.freebox.composedays.ui.theme.ComposeDaysTheme
import fr.freebox.composedays.ui.theme.ExtendedTheme

@Composable
fun FbxPrimaryButton(text: String, modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = FbxButtonDefaults.primaryButtonColors,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun FbxSecondaryButton(text: String, modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        colors = FbxButtonDefaults.secondaryButtonColors,
        border = FbxButtonDefaults.outlinedButtonBorder(enabled = enabled),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun FbxLinkButton(text: String, modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit = {}) {
    TextButton(onClick = onClick, modifier = modifier, enabled = enabled, colors = FbxButtonDefaults.textButtonColors) {
        Box(Modifier, contentAlignment = Alignment.TopCenter) {
            Text(text = text, textDecoration = TextDecoration.Underline)
        }
    }
}

@Composable
fun FbxActionButton(text: String, icon: Int, modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = FbxButtonDefaults.actionButtonColors,
        enabled = enabled,
        border = FbxButtonDefaults.actionButtonBorder(enabled = enabled),
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = text, Modifier.size(16.dp))
        Text(text = text)
    }
}

private object FbxButtonDefaults {
    val primaryButtonColors: ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.onTertiary
        )

    val secondaryButtonColors: ButtonColors
        @Composable
        get() = ButtonDefaults.outlinedButtonColors(
            contentColor = ExtendedTheme.colors.onBackgroundStroke,
            disabledContentColor = ExtendedTheme.colors.onBackgroundDisabled
        )

    val textButtonColors: ButtonColors
        @Composable
        get() = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = ExtendedTheme.colors.onBackgroundDisabled
        )

    val actionButtonColors: ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = ExtendedTheme.colors.onSurfaceStrong,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = ExtendedTheme.colors.onBackgroundDisabled,
        )

    @Composable
    fun outlinedButtonBorder(enabled: Boolean): BorderStroke {
        return BorderStroke(
            width = 1.dp,
            color = if (enabled) ExtendedTheme.colors.onBackgroundStroke else ExtendedTheme.colors.onBackgroundDisabled,
        )
    }

    @Composable
    fun actionButtonBorder(enabled: Boolean): BorderStroke {
        return BorderStroke(
            width = 1.dp,
            color = if (enabled) ExtendedTheme.colors.onSurfaceStrokeButton else ExtendedTheme.colors.onSurfaceDisabled,
        )
    }
}

// =======
// Preview
// =======

@Composable
private fun PreviewBase() {
    ComposeDaysTheme {
        val fill = Modifier.fillMaxWidth()
        Surface {
            Column(modifier = fill.padding(16.dp)) {
                FbxPrimaryButton(modifier = fill, text = "Primary Enabled")
                FbxPrimaryButton(modifier = fill, text = "Primary Disabled", enabled = false)
                FbxSecondaryButton(modifier = fill, text = "Secondary Enabled")
                FbxSecondaryButton(modifier = fill, text = "Secondary Disabled", enabled = false)
                FbxLinkButton(modifier = fill, text = "Link Enabled")
                FbxLinkButton(modifier = fill, text = "Link Disabled", enabled = false)
                FbxActionButton(modifier = fill, icon = R.drawable.ic_launcher_foreground, text = "Action Enabled")
                FbxActionButton(modifier = fill, icon = R.drawable.ic_launcher_foreground, text = "Action Disabled", enabled = false)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LightPreview() {
    PreviewBase()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    PreviewBase()
}