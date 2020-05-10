package hu.bme.aut.lab.gibfood.model

import com.google.gson.annotations.SerializedName
import java.util.*

class RecipeList {
    /**
     */
    @SerializedName("data")
    var data: List<Recipe> = ArrayList()

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val recipeList = o as RecipeList
        return data == recipeList.data
    }

    override fun hashCode(): Int {
        return Objects.hash(data)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class RecipeList {\n")
        sb.append("    data: ").append(toIndentedString(data)).append("\n")
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