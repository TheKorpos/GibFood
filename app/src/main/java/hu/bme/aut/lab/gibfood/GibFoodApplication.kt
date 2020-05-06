package hu.bme.aut.lab.gibfood

import android.app.Application
import hu.bme.aut.lab.gibfood.ui.UIModule

class GibFoodApplication : Application() {
    lateinit var injector: GibFoodApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerGibFoodApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}