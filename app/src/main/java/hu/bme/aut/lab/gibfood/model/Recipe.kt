package hu.bme.aut.lab.gibfood.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*


class Recipe {
    /**
     */
    @SerializedName("ingredients")
    var ingredients: List<String> =
        ArrayList()
    /**
     */
    @SerializedName("name")
    var name: String? = null
    /**
     */
    @SerializedName("description")
    var description: String? = null
    /**
     */
    @SerializedName("id")
    var id: Long? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val recipe = o as Recipe
        return ingredients == recipe.ingredients &&
                name == recipe.name &&
                description == recipe.description &&
                id == recipe.id
    }

    override fun hashCode(): Int {
        return Objects.hash(ingredients, name, description, id)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Recipe {\n")
        sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}