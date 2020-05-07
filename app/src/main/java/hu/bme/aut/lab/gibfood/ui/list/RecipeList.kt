package hu.bme.aut.lab.gibfood.ui.list

import android.content.Context
import android.content.Intent
import android.media.Image
import android.media.ImageReader
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.SearchView
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.details.DetailActivity
import hu.bme.aut.lab.gibfood.ui.main.MainPresenter

import kotlinx.android.synthetic.main.activity_recipe_list.*
import kotlinx.android.synthetic.main.content_recipe_list.*
import javax.inject.Inject

class RecipeList : AppCompatActivity() , SearchView.OnQueryTextListener, RecipeListScreen {

    @Inject
    lateinit var recipeListPresenter: RecipeListPresenter

    private var recipeAdapter : RecipeAdapter? = null
    private  var visibleRecipes: MutableList<Recipe> = mutableListOf()

    val RECIPE_ID : String = "RECIPE_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)

        setContentView(R.layout.activity_recipe_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Adding recipes is not yet possible", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        searchView.setOnQueryTextListener(this)
    }

    override fun onStart() {
        super.onStart()
        recipeListPresenter.attachScreen(this)


        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewRecipes.layoutManager = llm

        recipeAdapter = RecipeAdapter(this, visibleRecipes)
        recyclerViewRecipes.adapter = recipeAdapter

        recipeListPresenter.refreshList()
    }

    override fun onStop() {
        super.onStop()
        recipeListPresenter.detachScreen()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            recipeListPresenter.refreshList(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            recipeListPresenter.refreshList(newText)
        }
        return false
    }

    override fun showRecipes(recipes: List<Recipe>) {
        visibleRecipes.clear()
        visibleRecipes.addAll(recipes)
        recipeAdapter?.notifyDataSetChanged()
    }

    fun openItem(recipe: Recipe ){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(RECIPE_ID, recipe.id)
        startActivity(intent)
    }
}
