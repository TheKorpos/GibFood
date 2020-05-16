package hu.bme.aut.lab.gibfood.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class RecipeWithIngredients(
    @Embedded
    var recipe: Recipe,

    @Relation(
        parentColumn = "id",
        entityColumn = "belongs_to"
    )

    var ingredients: List<IngredientListItem>
)