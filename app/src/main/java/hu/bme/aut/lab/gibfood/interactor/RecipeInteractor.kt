package hu.bme.aut.lab.gibfood.interactor

import hu.bme.aut.lab.gibfood.interactor.event.RecipeEvent
import hu.bme.aut.lab.gibfood.interactor.event.RecipeListEvent
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.model.RecipeList
import hu.bme.aut.lab.gibfood.network.RecipeApi
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import javax.inject.Inject

class RecipeInteractor @Inject constructor(private val recipeApi: RecipeApi) {


    fun getRecipes() {
        val call = recipeApi.recipeGet()
        doGetRecipeList(call)
    }

    fun getRecipes(query: String) {
        val call = recipeApi.recipeFilteredGet(query)
        doGetRecipeList(call)
    }

    private fun doGetRecipeList(call: Call<RecipeList>){
        val event = RecipeListEvent()

        try {
            val response = call.execute()
            if (response.code() != 200){
                throw Exception(response.errorBody()?.toString())
            }
            event.code = 200
            response.body()?.let { event.recipes = it.data }
            EventBus.getDefault().post(event)
        } catch (e: Exception){
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getRecipe(recipeId: Long) {
        val call = recipeApi.recipeFindById(recipeId)
        val event = RecipeEvent()

        try {
            val response = call.execute()
            if (response.code() != 200){
                throw Exception(response.errorBody()?.toString())
            }
            event.code = 200
            response.body()?.let { event.recipe = it }
            EventBus.getDefault().post(event)
        } catch (e: Exception){
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun postRecipe(name: String, ingredients: List<String>, description: String){

        val newRecipe = Recipe()
        newRecipe.name = name
        newRecipe.description = description
        newRecipe.ingredients = ingredients

        val event = RecipeEvent()

        val call = recipeApi.recipeCreate("", newRecipe)

        try {
            val response = call.execute()
            if (response.code() != 200){
                throw Exception(response.errorBody()?.toString())
            }
            event.code = 200
            EventBus.getDefault().post(event)
        } catch (e: Exception){
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

}