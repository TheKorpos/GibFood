package hu.bme.aut.lab.gibfood.ui.details

interface DetailScreen {

    fun showRecipeName(name: String)
    fun showIngredients(ingredients: List<String>)
    fun showDescription(description: String)

}