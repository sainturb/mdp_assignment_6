package miu.edu.foodieapp.ui.recipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.Ingredient
import miu.edu.foodieapp.domain.Recipe
import miu.edu.foodieapp.domain.User
import miu.edu.foodieapp.utils.RecipeDataSource
import java.util.Calendar
import kotlin.random.Random

class RecipesViewModel(val dataSource: RecipeDataSource) : ViewModel() {

    val recipesLiveData = dataSource.getRecipeList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertRecipe(name: String?, instruction: String?, ingredients: List<Ingredient>, user: User, cookingTime: Int?): Boolean {
        if (name == null || instruction == null || ingredients.isEmpty()) {
            return false
        }

        val recipe = Recipe(
            Random.nextInt(),
            name,
            R.drawable.wallemart,
            instruction,
            ingredients,
            user,
            Calendar.getInstance().time,
            cookingTime ?: 60,
            0.0
        )
        println(recipe.toString())
        dataSource.addRecipe(recipe)
        return true
    }
}

class RecipesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipesViewModel(
                dataSource = RecipeDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}