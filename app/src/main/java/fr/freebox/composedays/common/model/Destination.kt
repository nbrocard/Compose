package fr.freebox.composedays.common.model

import android.util.Log
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation

interface Destination<T : Arguments>

inline fun <reified T : Arguments> Destination<T>.route(): String {
    val path = T::class.declaredMemberProperties
        .mapNotNull { field ->
            field.takeIf { it.hasAnnotation<Path>() }
        }
        .map { it.name }
        .sorted()
        .joinToString("/") { "{$it}" }
    return "${this::class.java.simpleName}/$path".also {
        Log.d("TESTOUZE", "route : $it")
    }
}

inline fun <reified T : Arguments> Destination<T>.route(args: T): String {
    val path = T::class.declaredMemberProperties
        .mapNotNull { field ->
            field.takeIf { it.hasAnnotation<Path>() }
        }
        .sortedBy { it.name }
        .map { it.get(args) }
        .joinToString("/")
    return "${this::class.java.simpleName}/$path".also {
        Log.d("TESTOUZE", "routing to $it")
    }
}