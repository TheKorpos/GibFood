package hu.bme.aut.lab.gibfood

import dagger.Component
import hu.bme.aut.lab.gibfood.interactor.InteractorModule
import hu.bme.aut.lab.gibfood.network.NetworkModule
import hu.bme.aut.lab.gibfood.ui.UIModule
import hu.bme.aut.lab.gibfood.ui.add.AddActivity
import hu.bme.aut.lab.gibfood.ui.details.DetailActivity
import hu.bme.aut.lab.gibfood.ui.list.RecipeList
import hu.bme.aut.lab.gibfood.ui.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [UIModule::class, InteractorModule::class, NetworkModule::class])
interface GibFoodApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(recipeList: RecipeList)
    fun inject(detailActivity: DetailActivity)
    fun inject(addActivity: AddActivity)
}