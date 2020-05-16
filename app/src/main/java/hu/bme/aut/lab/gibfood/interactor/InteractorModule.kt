package hu.bme.aut.lab.gibfood.interactor

import dagger.Module
import dagger.Provides
import hu.bme.aut.lab.gibfood.network.RecipeApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun eventBus() = EventBus.getDefault()

    @Provides
    @Singleton
    fun recipeInteractor(recipeApi: RecipeApi, eventBus: EventBus) = RecipeInteractor(recipeApi, eventBus)
}