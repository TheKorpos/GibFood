package hu.bme.aut.lab.gibfood.interactor.event

import hu.bme.aut.lab.gibfood.model.Recipe

data class RecipeEvent (
    var code: Int = 0,
    var recipes: List<Recipe> = listOf(),
    var throwable: Throwable? = null
)