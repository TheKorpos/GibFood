package hu.bme.aut.lab.gibfood

import dagger.Component
import hu.bme.aut.lab.gibfood.ui.UIModule
import hu.bme.aut.lab.gibfood.ui.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [UIModule::class])
interface GibFoodApplicationComponent {

    fun inject(mainActivity: MainActivity)

}