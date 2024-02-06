package fr.freebox.composedays.details.model

import fr.freebox.composedays.common.model.Arguments
import fr.freebox.composedays.common.model.Destination
import fr.freebox.composedays.common.model.Path

object TrucDetailsDirection : Destination<TrucDetailsArguments>

data class TrucDetailsArguments(@Path val index: Int) : Arguments