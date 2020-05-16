package hu.bme.aut.lab.gibfood.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.list.RecipeList
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class DetailActivity : AppCompatActivity(), DetailScreen {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    private val ingredientList: MutableList<String> = mutableListOf()

    private var recipeId: Long? = null
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        injector.inject(this)

        recipeId = intent.getLongExtra("RECIPE_ID", -1)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientList)
        detailsListView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        detailPresenter.getRecipe(recipeId!!)
    }

    override fun onStart() {
        super.onStart()
        detailPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        detailPresenter.detachScreen()
    }

    override fun showRecipeName(name: String) {
        detailRecipeName.text = name
    }

    override fun showIngredients(ingredients: List<String>) {
       ingredientList.addAll(ingredients)
        adapter?.notifyDataSetChanged()
    }

    override fun showDescription(description: String) {
       detailsDescription.text = description
    }

    override fun showErrorNotif(notif: String) {
        Snackbar.make(addRecipeMainView, notif, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}