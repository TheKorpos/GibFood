package hu.bme.aut.lab.gibfood.ui.list

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.interactor.event.RecipeListEvent
import hu.bme.aut.lab.gibfood.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class RecipeListPresenter @Inject constructor(private val executor: Executor, private val recipeInteractor: RecipeInteractor) : Presenter<RecipeListScreen>() {

    override fun attachScreen(screen: RecipeListScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        super.detachScreen()
        EventBus.getDefault().unregister(this)
    }

    fun refreshList(){
        executor.execute{
            recipeInteractor.getRecipes()
        }
    }

    fun refreshList(text: String){
        if (text.isNotEmpty()){
            executor.execute{
                recipeInteractor.getRecipes(text)
            }
        } else {
            refreshList()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(listEvent: RecipeListEvent) {
        if (listEvent.throwable != null) {
            listEvent.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showErrorNotif(listEvent.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (listEvent.recipes.isNotEmpty()) {
                    screen!!.showRecipes(listEvent.recipes)
                }

            }
        }
    }
}