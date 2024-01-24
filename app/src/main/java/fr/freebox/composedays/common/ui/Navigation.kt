package fr.freebox.composedays.common.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.freebox.composedays.common.model.HomeDestination
import fr.freebox.composedays.common.model.MainGraph
import fr.freebox.composedays.common.model.mainGraph
import fr.freebox.composedays.common.model.route

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route(), modifier = Modifier.fillMaxSize()
    ) {
        mainGraph(navController)
    }
}