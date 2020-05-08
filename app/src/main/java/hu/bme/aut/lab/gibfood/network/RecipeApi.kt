package hu.bme.aut.lab.gibfood.network

import hu.bme.aut.lab.gibfood.model.InlineResponse200
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.model.RecipeList
import retrofit2.Call
import retrofit2.http.*
import java.math.BigDecimal

interface RecipeApi {
    /**
     * Get all recipes
     *
     * @return Call<RecipeList>
    </RecipeList> */
    @GET("Recipes")
    @Headers("Accept: application/json")
    fun recipeGet(): Call<RecipeList>

    /**
     * Create a new instance of the model and persist it into the data source.
     *
     * @param accessToken User access token
     * @param data Model instance data
     * @return Call<Recipe>
    </Recipe> */
    @POST("Recipes")
    fun recipeCreate(
        @Query("accessToken") accessToken: String, @Body data: Recipe
    ): Call<Recipe>

    /**
     * Count instances of the model.
     *
     * @return Call<InlineResponse200>
    </InlineResponse200> */
    @GET("Recipes/count")
    fun recipeCount(): Call<InlineResponse200>

    /**
     * Find a model instance by id from the data source.
     *
     * @param id Model id
     * @return Call<Recipe>
    </Recipe> */
    @GET("Recipes/{id}")
    fun recipeFindById(
        @Path("id") id: BigDecimal
    ): Call<Recipe>

    /**
     * Check whether a model instance exists in the data source.
     *
     * @param id Model id
     * @return Call<InlineResponse200>
    </InlineResponse200> */
    @HEAD("Recipes/{id}")
    fun recipeExistsHeadRecipesId(
        @Path("id") id: BigDecimal
    ): Call<InlineResponse200>

    /**
     * Update attributes for a model instance and persist it into the data source.
     *
     * @param accessToken User access token
     * @param id PersistedModel id
     * @param data An object of model property name/value pairs
     * @return Call<Recipe>
    </Recipe> */
    @PUT("Recipes/{id}")
    fun recipePrototypeUpdateAttributes(
        @Query("accessToken") accessToken: String?, @Path("id") id: BigDecimal, @Body data: Recipe
    ): Call<Recipe>

    /**
     * Delete a model instance by id from the data source.
     *
     * @param id Model id
     * @param accessToken User access token
     * @return Call<InlineResponse200>
    </InlineResponse200> */
    @DELETE("Recipes/{id}")
    fun recipeDeleteById(
        @Path("id") id: BigDecimal, @Query("accessToken") accessToken: String
    ): Call<InlineResponse200>
}