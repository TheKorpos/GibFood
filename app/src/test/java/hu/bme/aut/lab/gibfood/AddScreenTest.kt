package hu.bme.aut.lab.gibfood

import android.widget.TextView
import hu.bme.aut.lab.gibfood.ui.add.AddActivity
import kotlinx.android.synthetic.main.activity_add.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner



@RunWith(RobolectricTestRunner::class)
class AddScreenTest {

    @Test
    fun ingredientAddTest(){
        val activity = Robolectric.setupActivity(AddActivity::class.java)

        activity.addIngredient("inredient1")

       val firstChild = activity.ingredinet_list.layoutManager?.getChildAt(0)

       val textView = firstChild?.findViewById<TextView>(R.id.ingredient_list_iten_name)

        Assert.assertNotNull(textView)
        Assert.assertEquals("inredient1", textView?.text.toString())
    }

    @Test
    fun ingredientAddMoreTest(){
        val activity = Robolectric.setupActivity(AddActivity::class.java)

        activity.addIngredient("inredient1")
        activity.addIngredient("inredient2")
        activity.addIngredient("inredient3")

        val childCount = activity.ingredinet_list.layoutManager?.childCount

        Assert.assertEquals(3, childCount)
    }
}


