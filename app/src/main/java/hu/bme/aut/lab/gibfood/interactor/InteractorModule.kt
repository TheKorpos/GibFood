package hu.bme.aut.lab.gibfood.interactor

import dagger.Module
import dagger.Provides
import hu.bme.aut.lab.gibfood.network.RecipeApi
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun recipeInteractor(recipeApi: RecipeApi) = RecipeInteractor(recipeApi)
}