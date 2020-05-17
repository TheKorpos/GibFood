package hu.bme.aut.lab.gibfood.ui.add

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.injector
import kotlinx.android.synthetic.main.activity_add.*
import javax.inject.Inject

class AddActivity : AppCompatActivity(), AddScreen {

    private val ingredients : MutableList<String> = mutableListOf()
    private var adapter: IngredientAdapter? = null

    @Inject
    lateinit var  addRecipePresenter: AddRecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        injector.inject(this)

        val ll = LinearLayoutManager(this)
        ll.orientation = RecyclerView.VERTICAL
        ingredinet_list.layoutManager = ll

        adapter = IngredientAdapter(this, ingredients)

        ingredinet_list.adapter = this.adapter

        btnIngredientAdd.setOnClickListener{
            val ingredient = newIngredient.text.toString()
            addIngredient(ingredient)
        }
    }

    override fun onStart() {
        super.onStart()
        addRecipePresenter.attachScreen(this)

        btnRecipeInsert.setOnClickListener{
            addRecipePresenter.insertRecipe(addRecipeName.text.toString(), addRecipeDescription.text.toString(), ingredients)

        }
    }



    override fun onStop() {
        super.onStop()
        addRecipePresenter.detachScreen()
    }

    override fun removeIngredient(item: String){
        val pos = ingredients.indexOf(item)
        ingredients.remove(item)
        adapter?.notifyItemRemoved(pos)
    }

     override fun addIngredient(item: String){
        ingredients.add(item)
        adapter?.notifyItemInserted(ingredients.indexOf(item))
    }

    override fun navigateBack() {
       onNavigateUp()
    }

    override fun showErrorNotif(notif: String) {
        Snackbar.make(addRecipeMainView, notif, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}