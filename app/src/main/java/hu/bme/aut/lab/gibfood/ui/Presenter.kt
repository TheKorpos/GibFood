package hu.bme.aut.lab.gibfood.ui

abstract class Presenter<T> {
    protected var screen: T? = null

    open fun attachScreen(screen: T) {
        this.screen = screen
    }

    open fun detachScreen() {
        this.screen = null
    }
}