package hu.bme.aut.lab.gibfood.ui.details

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.interactor.event.RecipeEvent
import hu.bme.aut.lab.gibfood.interactor.event.RecipeListEvent
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val executor: Executor, private val recipeInteractor: RecipeInteractor) : Presenter<DetailScreen>() {

    override fun attachScreen(screen: DetailScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        super.detachScreen()
        EventBus.getDefault().unregister(this)
    }

    fun getRecipe(recipeId: Long) {
        executor.execute{
            recipeInteractor.getRecipe(recipeId)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(recipeEvent: RecipeEvent) {
        if (recipeEvent.throwable != null) {
            recipeEvent.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showErrorNotif(recipeEvent.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (recipeEvent.recipe != null) {
                    val recipe = recipeEvent.recipe
                    recipe?.name?.let { it -> screen?.showRecipeName(it) }
                    recipe?.ingredients?.let { it -> screen?.showIngredients(it) }
                    recipe?.description?.let { it -> screen?.showDescription(it) }
                }
            }
        }
    }
}