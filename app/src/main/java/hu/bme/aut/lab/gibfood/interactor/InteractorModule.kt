package hu.bme.aut.lab.gibfood.interactor

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun recipeInteractor() = RecipeInteractor()

}