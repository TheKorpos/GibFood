package hu.bme.aut.lab.gibfood.ui.list

import hu.bme.aut.lab.gibfood.model.Recipe

interface RecipeListScreen {
     fun showRecipes(recipes: List<Recipe>)
}