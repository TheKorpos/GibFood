package hu.bme.aut.lab.gibfood.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.details.DetailPresenter
import hu.bme.aut.lab.gibfood.ui.list.RecipeListPresenter
import hu.bme.aut.lab.gibfood.ui.main.MainPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Singleton

@Module
class UIModule (private val context: Context){

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()

    @Provides
    @Singleton
    fun recipeListPresenter(executor: Executor, recipeInteractor: RecipeInteractor) = RecipeListPresenter(executor, recipeInteractor)

    @Provides
    @Singleton
    fun detailPresenter(recipeInteractor: RecipeInteractor) = DetailPresenter(recipeInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}