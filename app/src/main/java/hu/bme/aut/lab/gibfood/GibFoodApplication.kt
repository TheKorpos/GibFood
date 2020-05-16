package hu.bme.aut.lab.gibfood

import android.app.Application
import androidx.room.Room
import hu.bme.aut.lab.gibfood.interactor.InteractorModule
import hu.bme.aut.lab.gibfood.room.DatabaseModule
import hu.bme.aut.lab.gibfood.room.RoomDB
import hu.bme.aut.lab.gibfood.ui.UIModule

class GibFoodApplication : Application() {
    lateinit var injector: GibFoodApplicationComponent

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            this,
            RoomDB::class.java,
            "recipe_db"
        ).build()

        injector = DaggerGibFoodApplicationComponent.builder().uIModule(UIModule(this)).databaseModule(
            DatabaseModule(db)
        ).build()
    }
}