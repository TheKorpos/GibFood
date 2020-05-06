package hu.bme.aut.lab.gibfood.interactor

import hu.bme.aut.lab.gibfood.model.Recipe
import javax.inject.Inject

class RecipeInteractor {

    private val mutableList = mutableListOf(
        Recipe(0, "First"),
        Recipe(1, "Second"),
        Recipe(2, "Third"),
        Recipe(3, "Another"),
        Recipe(4, "First"),
        Recipe(5, "First"),
        Recipe(6, "First"),
        Recipe(7, "First"),
        Recipe(8,"First")
    )

    fun getRecipes() : List<Recipe> {
        return mutableList
    }

    fun getRecipes(query: String): List<Recipe> {
      return mutableList.filter { it.name.contains(query) }
    }
}