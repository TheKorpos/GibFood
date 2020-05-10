package hu.bme.aut.lab.gibfood.room

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.lab.gibfood.room.dao.RecipeDao

@Database(entities = arrayOf(Recipe::class, IngredientListItem::class), version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}