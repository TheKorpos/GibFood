package hu.bme.aut.lab.gibfood.ui.details

import hu.bme.aut.lab.gibfood.ui.Presenter

class DetailPresenter : Presenter<DetailScreen>() {

    fun getRecipe(recipeId: Int) {
        screen?.showRecipeName("Puliszka")
        screen?.showIngredients(listOf("sugar", "milk", "something"))
        screen?.showDescription("lorem200")
    }
}