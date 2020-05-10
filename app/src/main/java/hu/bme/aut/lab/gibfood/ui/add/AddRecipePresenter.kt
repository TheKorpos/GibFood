package hu.bme.aut.lab.gibfood.ui.add

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.interactor.event.RecipeEvent
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class AddRecipePresenter @Inject constructor(private val executor: Executor, private val recipeInteractor: RecipeInteractor) : Presenter<AddScreen>(){

    override fun attachScreen(screen: AddScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        super.detachScreen()
        EventBus.getDefault().unregister(this)
    }


    fun insertRecipe(name: String, description: String, ingredients: List<String>){
        executor.execute{
            recipeInteractor.postRecipe(name, ingredients, description)
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
            screen?.navigateBack()
        }
    }
}