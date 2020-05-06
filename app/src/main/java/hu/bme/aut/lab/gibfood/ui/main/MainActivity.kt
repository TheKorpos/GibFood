package hu.bme.aut.lab.gibfood.ui.main


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.ui.list.RecipeList
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)

        btnLogin.setOnClickListener { mainPresenter.attemptLogin(fieldUserName.text.toString(), fieldPassword.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this);
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun showRecipeList() {
        val intent = Intent(this, RecipeList::class.java)
        startActivity(intent)
    }
}
