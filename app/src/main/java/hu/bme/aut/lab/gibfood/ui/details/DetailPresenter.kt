package hu.bme.aut.lab.gibfood.ui.details

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.Presenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val recipeInteractor: RecipeInteractor) : Presenter<DetailScreen>() {

    fun getRecipe(recipeId: Int) {
        val recipe = recipeInteractor.getRecipes(recipeId)
        screen?.showRecipeName("Puliszka")
        screen?.showIngredients(listOf("sugar", "milk", "something"))
        screen?.showDescription("lorem200")
    }
}