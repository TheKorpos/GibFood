package hu.bme.aut.lab.gibfood.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.lab.gibfood.room.IngredientListItem
import hu.bme.aut.lab.gibfood.room.Recipe
import hu.bme.aut.lab.gibfood.room.RecipeWithIngredients

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>?

    @Query("SELECT * FROM recipe WHERE id IN (:recipeId)")
    fun loadAllByIds(recipeId: LongArray?): List<Recipe?>?

    @Query("SELECT * FROM ingredientlistitem WHERE id  == :recipeId")
    fun getIngredients(recipeId: Long): List<IngredientListItem>?

    @Insert
    fun insertAll(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllWithIngredients() : List<RecipeWithIngredients>?


   @Query("SELECT * FROM recipe WHERE name IN (:names)")
   fun getRecipesByNames(names: List<String>): List<Recipe>

}
