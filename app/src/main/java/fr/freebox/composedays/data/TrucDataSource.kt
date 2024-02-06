package fr.freebox.composedays.data

import fr.freebox.composedays.R
import fr.freebox.composedays.list.model.Truc
import kotlin.math.absoluteValue

object TrucDataSource {
    fun getTruc(index: Int): Truc {
        val num = (index / NAMES.size + 1).takeIf { it > 1 } ?: ""
        val box = NAMES[index % NAMES.size]
        val name = "$box $num"
        return Truc(name, index, SUBTITLES[name.hashCode().absoluteValue % SUBTITLES.size], IMAGES[index % IMAGES.size])
    }

    private val NAMES = arrayOf(
        "Freebox Ultra",
        "Freebox Pop",
        "Freebox Delta",
        "Freebox One",
        "Freebox Mini",
        "Freebox Revolution",
    )

    private val SUBTITLES = arrayOf(
        "Connectée",
        "Déconnectée",
        "En veille",
    )

    private val IMAGES = arrayOf(
        R.drawable.ultra,
        R.drawable.pop,
        R.drawable.delta,
        R.drawable.one,
        R.drawable.mini,
        R.drawable.revo,
    )
}