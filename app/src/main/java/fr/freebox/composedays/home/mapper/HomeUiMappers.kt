package fr.freebox.composedays.home.mapper

import fr.freebox.composedays.common.model.TopBarUi
import fr.freebox.composedays.home.model.Home
import fr.freebox.composedays.home.model.HomeUi

class HomeToTopBarUi : (Home) -> TopBarUi {
    override fun invoke(home: Home): TopBarUi {
        return TopBarUi("Home ${home.value}", null)
    }
}

class HomeToUi : (Home) -> HomeUi {
    override fun invoke(home: Home): HomeUi {
        return HomeUi(home.value > 0)
    }
}