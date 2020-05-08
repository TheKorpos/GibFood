package hu.bme.aut.lab.gibfood.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.lab.gibfood.R
import hu.bme.aut.lab.gibfood.model.Recipe
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipeAdapter(private val context: RecipeList, private val items: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, viewGroup, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        val recipe = items[pos]
        viewHolder.cardRecipeName.text = recipe.name

        viewHolder.itemView.setOnClickListener { view -> context.openItem(recipe)}
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val cardRecipeName = view.cardRecipeName
        val cardRecipeImage = view.cardRecipeImage
    }

}