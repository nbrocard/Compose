package fr.freebox.composedays.list.model

import fr.freebox.composedays.common.model.Arguments
import fr.freebox.composedays.common.model.Destination
import fr.freebox.composedays.common.model.Path

data class TrucListArguments(@Path val numberOfTrucs: Int, @Path val parent: String) : Arguments

object TrucDestination : Destination<TrucListArguments>