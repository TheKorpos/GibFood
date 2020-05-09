package hu.bme.aut.lab.gibfood.ui.add

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.Presenter
import javax.inject.Inject

class AddRecipePresenter @Inject constructor(private val recipeInteractor: RecipeInteractor) : Presenter<AddScreen>(){

    fun insertRecipe(name: String, description: String, ingredients: List<String>){
        screen?.showErrorNotif("Cannot insert")
    }



}