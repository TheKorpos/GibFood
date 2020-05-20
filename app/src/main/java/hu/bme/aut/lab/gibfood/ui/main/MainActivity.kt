package hu.bme.aut.lab.gibfood.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.lab.gibfood.GibFoodApplication
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.ui.list.RecipeList
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.RuntimeException
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)

        btnLogin.setOnClickListener { mainPresenter.attemptLogin(fieldUserName.text.toString(), fieldPassword.text.toString()) }

        val app = application as GibFoodApplication

        btnRegister.setOnClickListener{
            app.getAnalytics().logEvent(FirebaseAnalytics.Event.SIGN_UP, null)

            //test crash
            throw RuntimeException()
        }
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
