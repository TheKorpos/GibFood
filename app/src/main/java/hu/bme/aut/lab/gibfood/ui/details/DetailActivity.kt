package hu.bme.aut.lab.gibfood.ui.details

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import hu.bme.aut.lab.gibfood.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)
    }

}