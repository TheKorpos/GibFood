package hu.bme.aut.lab.gibfood.interactor

import hu.bme.aut.lab.gibfood.interactor.event.RecipeEvent
import hu.bme.aut.lab.gibfood.network.RecipeApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class RecipeInteractor @Inject constructor(private val recipeApi: RecipeApi) {


    fun getRecipes() {
        val call = recipeApi.recipeGet()
        val event = RecipeEvent()

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

    fun getRecipes(query: String) {
    }

    fun getRecipes(recipeId: Int){
    }

}