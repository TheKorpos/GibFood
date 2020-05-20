package hu.bme.aut.lab.gibfood.ui.list

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import hu.bme.aut.lab.gibfood.GibFoodApplication
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.ui.add.AddActivity
import hu.bme.aut.lab.gibfood.ui.details.DetailActivity
import kotlinx.android.synthetic.main.activity_add.*

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

        fab.setOnClickListener { openAddScreen() }

        searchView.setOnQueryTextListener(this)
    }

    override fun openAddScreen(){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        recipeListPresenter.attachScreen(this)


        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
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
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(RECIPE_ID, recipe.id)
            putExtra("KEY", "hello")
        }

        startActivity(intent)
    }

    override fun showErrorNotif(notif: String) {
        Snackbar.make(addRecipeMainView, notif, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
