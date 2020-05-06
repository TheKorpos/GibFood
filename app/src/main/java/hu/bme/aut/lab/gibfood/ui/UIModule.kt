package hu.bme.aut.lab.gibfood.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.aut.lab.gibfood.ui.main.MainPresenter
import javax.inject.Singleton

@Module
class UIModule (private val context: Context){

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()
}