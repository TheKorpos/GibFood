package hu.bme.aut.lab.gibfood

import android.app.Application
import androidx.room.Room
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.lab.gibfood.room.DatabaseModule
import hu.bme.aut.lab.gibfood.room.RoomDB
import hu.bme.aut.lab.gibfood.ui.UIModule


class GibFoodApplication : Application() {
    lateinit var injector: GibFoodApplicationComponent
    private lateinit var firebaseAnalytics: FirebaseAnalytics

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

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }


    fun getAnalytics() = firebaseAnalytics
}