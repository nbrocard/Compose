package fr.freebox.composedays.common.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith

val fadeTransition = fadeIn().togetherWith(fadeOut())