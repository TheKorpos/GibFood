package hu.bme.aut.lab.gibfood.ui.main

import hu.bme.aut.lab.gibfood.ui.Presenter

class MainPresenter: Presenter<MainScreen>() {

    fun attemptLogin(userName: String, password: String){
        screen?.showRecipeList()
    }

}