package hu.bme.aut.lab.gibfood.ui.list

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.Presenter
import javax.inject.Inject

class RecipeListPresenter @Inject constructor(private val recipeInteractor: RecipeInteractor) : Presenter<RecipeListScreen>() {

    fun refreshList(){
        val recipes = recipeInteractor.getRecipes()
        screen?.showRecipes(recipes)
    }

    fun refreshList(text: String){
        if (!text.isEmpty()){
            val recipes = recipeInteractor.getRecipes(text)
            screen?.showRecipes(recipes)
        } else {
            screen?.showRecipes(recipeInteractor.getRecipes())
        }
    }
}