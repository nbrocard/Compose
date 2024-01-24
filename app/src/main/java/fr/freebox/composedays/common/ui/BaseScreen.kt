package fr.freebox.composedays.common.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import fr.freebox.composedays.common.model.LoadingState
import fr.freebox.composedays.common.model.TopBarUi


@Composable
fun BaseScreen(topBar: TopBarUi, onNavigateUp: () -> Unit = {}, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            FbxTitleBar(topBar.title, topBar.navIcon, navigateUp = onNavigateUp)
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding), content = content)
    }
}

@Composable
fun <T> LoadingBaseScreen(topBar: TopBarUi, loadingState: LoadingState<T>, onNavigateUp: () -> Unit = {}, content: @Composable (T) -> Unit) {
    BaseScreen(topBar = topBar, onNavigateUp = onNavigateUp) {
        FbxLoading(state = loadingState, content = content)
    }
}

@Composable
fun SimpleBaseScreen(title: String, showBackButton: Boolean = true, content: @Composable () -> Unit) {
    BaseScreen(
        topBar = TopBarUi(title, Icons.Filled.ArrowBack.takeIf { showBackButton }),
        content = content
    )
}

@Composable
fun <T> SimpleLoadingBaseScreen(title: String, showBackButton: Boolean = true, loadingState: LoadingState<T>, content: @Composable (T) -> Unit) {
    LoadingBaseScreen(
        topBar = TopBarUi(title, Icons.Filled.ArrowBack.takeIf { showBackButton }),
        loadingState = loadingState,
        content = content
    )
}

@Composable
private fun FbxTitleBar(title: String, navIcon: ImageVector? = null, navigateUp: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(),
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = it, contentDescription = "Nav")
                }
            }
        }
    )
}

@Composable
private fun <T> FbxLoading(state: LoadingState<T>, content: @Composable (T) -> Unit) {
    AnimatedContent(targetState = state, label = "Loading", transitionSpec = { fadeTransition }) { targetState ->
        when (targetState) {
            is LoadingState.Done -> content(targetState.data)
            is LoadingState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}