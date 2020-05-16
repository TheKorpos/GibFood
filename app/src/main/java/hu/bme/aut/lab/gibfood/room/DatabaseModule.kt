package hu.bme.aut.lab.gibfood.room

import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val db: RoomDB) {

    @Provides
    fun db() = db

}