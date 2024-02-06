package fr.freebox.composedays.list.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import fr.freebox.composedays.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class Truc(val name: String, val index: Int, val subtitle: String, @DrawableRes val image: Int) :Parcelable