package fr.freebox.composedays.common.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
fun BaseScreen(topBar: TopBarUi, loadingState: LoadingState = LoadingState.Done, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            FbxTitleBar(topBar.title, topBar.navIcon)
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            FbxLoading(state = loadingState, content = content)
        }
    }
}

@Composable
fun SimpleBaseScreen(title: String, showBackButton: Boolean = true, loadingState: LoadingState = LoadingState.Done, content: @Composable () -> Unit) {
    BaseScreen(
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
private fun FbxLoading(state: LoadingState, content: @Composable () -> Unit) {
    AnimatedContent(targetState = state, label = "Loading", transitionSpec = { fadeTransition }) { targetState ->
        when (targetState) {
            LoadingState.Done -> content()
            LoadingState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}