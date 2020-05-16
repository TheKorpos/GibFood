package hu.bme.aut.lab.gibfood

import hu.bme.aut.lab.gibfood.interactor.RecipeInteractor
import hu.bme.aut.lab.gibfood.ui.list.RecipeListPresenter
import hu.bme.aut.lab.gibfood.ui.list.RecipeListScreen
import hu.bme.aut.lab.gibfood.util.MainThreadExecutor
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mock.*
import  org.mockito.Mockito
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService


class RecipeListPresenterTest {

    @Test
    fun refreshScreenTest() {
        val mockedInteractror = Mockito.mock(RecipeInteractor::class.java)
        val mockedView = Mockito.mock(RecipeListScreen::class.java)

        val presenter = RecipeListPresenter(MainThreadExecutor(), mockedInteractror)
        presenter.attachScreen(mockedView)

        presenter.refreshList()

        Mockito.verify(mockedInteractror, Mockito.times(1)).getRecipes()
    }

    @Test
    fun refreshScreenFilteredTest() {
        val mockedInteractror = Mockito.mock(RecipeInteractor::class.java)
        val mockedView = Mockito.mock(RecipeListScreen::class.java)

        val presenter = RecipeListPresenter(MainThreadExecutor(), mockedInteractror)
        presenter.attachScreen(mockedView)

        presenter.refreshList("filter")

        Mockito.verify(mockedInteractror, Mockito.times(1)).getRecipes("filter")
    }

    @Test
    fun refreshScreenFilteredEmptyTest() {
        val mockedInteractror = Mockito.mock(RecipeInteractor::class.java)
        val mockedView = Mockito.mock(RecipeListScreen::class.java)

        val presenter = RecipeListPresenter(MainThreadExecutor(), mockedInteractror)
        presenter.attachScreen(mockedView)

        presenter.refreshList("")

        Mockito.verify(mockedInteractror, Mockito.times(1)).getRecipes()
    }
}