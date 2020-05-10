package hu.bme.aut.lab.gibfood.room

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded
    var recipe: Recipe,

    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )

    var ingredients: List<IngredientListItem>
)