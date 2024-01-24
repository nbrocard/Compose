package fr.freebox.composedays.common.model

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.freebox.composedays.home.ui.HomeScreen
import fr.freebox.composedays.list.model.TrucListArguments
import fr.freebox.composedays.list.ui.ListScreen
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.javaField


sealed interface MainGraph {
    sealed interface Home : MainGraph {
        data class TrucList(val args: TrucListArguments) : Home
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    homeScreen(navController)
    trucListScreen(navController)
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(HomeDestination.route()) {
        HomeScreen {
            when (it) {
                is MainGraph.Home.TrucList -> navController.navigateToTrucList(it.args)
            }
        }
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Optional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Path

interface Arguments

interface Destination<T>

inline fun <reified T : Any> Destination<T>.route(): String {
    val path = T::class.declaredMemberProperties
        .mapNotNull { field ->
            field.takeIf { it.hasAnnotation<Path>() }
        }
        .map { it.name }
        .sorted()
        .joinToString("/", prefix = "{", postfix = "}")
    return "${this::class.java.simpleName}/$path"
}

inline fun <reified T : Any> Destination<T>.route(args: T): String {
    val path = T::class.declaredMemberProperties
        .mapNotNull { field ->
            field.takeIf { it.hasAnnotation<Path>() }
        }
        .sortedBy { it.name }
        .map { it.get(args) }
        .joinToString("/")
    return "${this::class.java.simpleName}/$path"
}


object TrucDestination : Destination<TrucListArguments>
object HomeDestination : Destination<Unit>

fun NavGraphBuilder.trucListScreen(navController: NavController) {
    composable(
        TrucDestination.route(),
        arguments = listOf(navArgument(TrucListArguments::numberOfTrucs.name) { type = NavType.IntType })
    ) {
        ListScreen()
    }
}

fun NavController.navigateToTrucList(args: TrucListArguments) {
    navigate(TrucDestination.route(args))
}