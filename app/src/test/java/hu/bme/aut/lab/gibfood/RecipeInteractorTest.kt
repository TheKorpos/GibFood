package hu.bme.aut.lab.gibfood

import android.util.EventLog
import com.google.gson.Gson
import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.interactor.event.RecipeEvent
import hu.bme.aut.lab.gibfood.interactor.event.RecipeListEvent
import hu.bme.aut.lab.gibfood.model.Recipe
import hu.bme.aut.lab.gibfood.model.RecipeList
import hu.bme.aut.lab.gibfood.network.RecipeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.greenrobot.eventbus.EventBus
import org.junit.*
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class RecipeInteractorTest {

    private var mockWebServer = MockWebServer()

    private lateinit var apiService: RecipeApi

    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Before
    fun setup() {
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse? {
               return when(request.path){
                    in "/Recipes/1" -> {
                        MockResponse()
                            .setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody("{\"id\" : 1, \"name\" : \"testname\", \"description\" : \"testDescription\", \"ingredients\" : []}")
                    }

                    in "/Recipes" -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody("{ \"data\" : [ {\"name\" : \"testname\", \"description\" : \"testDescription\", \"ingredients\" : []} ] }")
                     }

                   in "/Recipes/filter/a" -> {

                       val rl = RecipeList()
                       val recipe = Recipe()
                       recipe.id = 1
                       recipe.name = "a"
                       recipe.description = "testDescription"

                       rl.data = listOf(recipe)

                       MockResponse()
                           .setResponseCode(HttpURLConnection.HTTP_OK)
                           .setBody(Gson().toJson(rl))
                   }

                    else -> MockResponse().setResponseCode(404)
                }

                }
            }

        mockWebServer.setDispatcher(dispatcher)
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(RecipeApi::class.java)
    }


    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getRecipeTest(){

        val bus = Mockito.mock(EventBus::class.java)
        val recipeInteractor = RecipeInteractor(apiService, bus)

        recipeInteractor.getRecipes()

        Mockito.verify(bus).post(Mockito.any(RecipeListEvent::class.java))
    }

    @Test
    fun getRecipeIdTest(){
        val bus = Mockito.mock(EventBus::class.java)
        val recipeInteractor = RecipeInteractor(apiService, bus)

        recipeInteractor.getRecipe(1)

        val r = Recipe()
        r.name = "testname"
        r.description = "testDescription"
        r.id = 1
        r.ingredients = listOf()

       val event =  RecipeEvent(200, r, null)

        Mockito.verify(bus, Mockito.times(1)).post(event)
    }

    @Test
    fun getRecipeQueryTest(){
        val bus = Mockito.mock(EventBus::class.java)
        val recipeInteractor = RecipeInteractor(apiService, bus)

        recipeInteractor.getRecipes("a")

        val r = Recipe()
        r.name = "a"
        r.description = "testDescription"
        r.id = 1
        r.ingredients = listOf()

        val event =  RecipeListEvent(200, listOf(r), null)

        Mockito.verify(bus, Mockito.times(1)).post(event)
    }


}