package hu.bme.aut.lab.gibfood

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.add.AddRecipePresenter
import hu.bme.aut.lab.gibfood.ui.add.AddScreen
import hu.bme.aut.lab.gibfood.ui.list.RecipeListPresenter
import hu.bme.aut.lab.gibfood.ui.list.RecipeListScreen
import hu.bme.aut.lab.gibfood.util.MainThreadExecutor
import org.junit.Test
import org.mockito.Mockito

class AddPresenterTest {

    @Test
    fun refreshScreenTest() {
        val mockedInteractror = Mockito.mock(RecipeInteractor::class.java)
        val mockedView = Mockito.mock(AddScreen::class.java)

        val presenter = AddRecipePresenter(MainThreadExecutor(), mockedInteractror)
        presenter.attachScreen(mockedView)

        presenter.insertRecipe("testName", "testDesc", listOf("test item"))

        Mockito.verify(mockedInteractror, Mockito.times(1)).postRecipe("testName", listOf("test item"), "testDesc")
    }
}