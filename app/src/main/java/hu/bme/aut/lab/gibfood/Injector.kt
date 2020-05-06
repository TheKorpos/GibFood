package hu.bme.aut.lab.gibfood

import android.app.Activity
import android.support.v4.app.Fragment

val Activity.injector: GibFoodApplicationComponent
    get() {
        return (this.applicationContext as GibFoodApplication).injector
    }


val Fragment.injector: GibFoodApplicationComponent
    get() {
        return (this.context!!.applicationContext as GibFoodApplication).injector
    }