package hu.bme.aut.lab.gibfood

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: GibFoodApplicationComponent
    get() {
        return (this.applicationContext as GibFoodApplication).injector
    }


val androidx.fragment.app.Fragment.injector: GibFoodApplicationComponent
    get() {
        return (this.context!!.applicationContext as GibFoodApplication).injector
    }