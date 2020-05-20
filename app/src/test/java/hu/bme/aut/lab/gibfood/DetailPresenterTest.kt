package hu.bme.aut.lab.gibfood

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.details.DetailPresenter
import hu.bme.aut.lab.gibfood.ui.details.DetailScreen
import hu.bme.aut.lab.gibfood.ui.list.RecipeListPresenter
import hu.bme.aut.lab.gibfood.ui.list.RecipeListScreen
import hu.bme.aut.lab.gibfood.util.MainThreadExecutor
import org.junit.Test
import org.mockito.Mockito

class DetailPresenterTest {

    @Test
    fun getRecipeTest(){
        val mockedInteractror = Mockito.mock(RecipeInteractor::class.java)
        val mockedView = Mockito.mock(DetailScreen::class.java)

        val presenter = DetailPresenter(MainThreadExecutor(), mockedInteractror)
        presenter.attachScreen(mockedView)

        presenter.getRecipe(1)

        Mockito.verify(mockedInteractror, Mockito.times(1)).getRecipe(1)
    }

}