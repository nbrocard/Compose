package fr.freebox.composedays.common.model

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.starProjectedType

interface Arguments

object NoArgument : Arguments

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Path

inline fun <reified T : Arguments> navArgs(): List<NamedNavArgument> {
    return T::class.declaredMemberProperties
        .filter { it.hasAnnotation<Path>() }
        .map {
            navArgument(it.name) {
                type = when (it.getter.returnType) {
                    INT_TYPE -> NavType.IntType
                    STRING_TYPE -> NavType.StringType
                    BOOLEAN_TYPE -> NavType.BoolType
                    LONG_TYPE -> NavType.LongType
                    FLOAT_TYPE -> NavType.FloatType
                    else -> throw IllegalArgumentException("Unsupported type for argument property ${it.name}: ${it.getter.returnType}")
                }
            }
        }
}

val INT_TYPE = Int::class.starProjectedType
val LONG_TYPE = Long::class.starProjectedType
val FLOAT_TYPE = Float::class.starProjectedType
val STRING_TYPE = String::class.starProjectedType
val BOOLEAN_TYPE = Boolean::class.starProjectedType