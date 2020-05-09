package hu.bme.aut.lab.gibfood.ui.add

interface AddScreen {
    fun removeIngredient(item: String)
    fun addIngredient(item: String)
    fun navigateBack()
    fun showErrorNotif(notif: String)
}