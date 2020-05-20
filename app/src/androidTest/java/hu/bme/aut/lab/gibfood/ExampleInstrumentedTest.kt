package hu.bme.aut.lab.gibfood


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.lab.gibfood.room.Recipe
import hu.bme.aut.lab.gibfood.room.RoomDB


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hu.bme.aut.lab.gibfood", appContext.packageName)
    }

    @Test
    fun roomTest(){

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = Room.inMemoryDatabaseBuilder(
            appContext,
            RoomDB::class.java
        ).build()


       val recipe =  Recipe(1, "test", "anothertest")
        db.recipeDao().insertAll(recipe)

        val result = db.recipeDao().loadAllByIds(longArrayOf(1))

        assertEquals(1, result?.size)
        assertEquals("test", result?.get(0)?.name)

        db.close()

    }
}
