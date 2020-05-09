package hu.bme.aut.lab.gibfood.ui.add

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import hu.bme.aut.lab.gibfood.R
import kotlinx.android.synthetic.main.ingredient_list_item.view.*

class IngredientAdapter(private val context: AddActivity, private  val items: MutableList<String>) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): IngredientViewHolder {
      val view =  LayoutInflater.from(context).inflate(R.layout.ingredient_list_item, viewGroup, false)
      return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: IngredientViewHolder, pos: Int) {
        viewHolder.ingredientName.text = items[pos]
        viewHolder.ingredientBtn.setOnClickListener { context.removeIngredient(items[pos]) }
    }

    class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ingredientName: TextView = view.ingredient_list_iten_name
        val ingredientBtn: Button = view.ingredient_list_remove
    }

}