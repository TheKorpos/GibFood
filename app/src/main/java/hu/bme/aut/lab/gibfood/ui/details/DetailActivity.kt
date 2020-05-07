package hu.bme.aut.lab.gibfood.ui.details

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.ui.list.RecipeList
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class DetailActivity : AppCompatActivity(), DetailScreen {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    private val ingredientList: MutableList<String> = mutableListOf()

    private var recipeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        injector.inject(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientList)
        detailsListView.adapter = adapter

       recipeId = intent.getIntExtra(RecipeList::RECIPE_ID.toString(), -1)
    }

    override fun onStart() {
        super.onStart()
        detailPresenter.attachScreen(this)
        detailPresenter.getRecipe(recipeId!!)
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
        detailsListView.deferNotifyDataSetChanged()
    }

    override fun showDescription(description: String) {
       detailsDescription.text = description
    }
}