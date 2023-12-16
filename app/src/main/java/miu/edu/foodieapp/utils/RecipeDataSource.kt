package miu.edu.foodieapp.utils

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import miu.edu.foodieapp.domain.Recipe
import miu.edu.foodieapp.faker.recipeList

class RecipeDataSource(resources: Resources) {
    private val initialRecipeList = recipeList(resources)
    private val recipesLiveData = MutableLiveData(initialRecipeList)

    /* Adds recipe to liveData and posts value. */
    fun addRecipe(recipe: Recipe) {
        val currentList = recipesLiveData.value
        if (currentList == null) {
            recipesLiveData.postValue(listOf(recipe))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, recipe)
            recipesLiveData.postValue(updatedList)
        }
    }

    /* Removes recipe from liveData and posts value. */
    fun removeRecipe(recipe: Recipe) {
        val currentList = recipesLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(recipe)
            recipesLiveData.postValue(updatedList)
        }
    }

    /* Returns recipe given an ID. */
    fun getRecipeForId(id: Int): Recipe? {
        recipesLiveData.value?.let { list ->
            return list.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getRecipeList(): LiveData<List<Recipe>> {
        return recipesLiveData
    }

    /* Returns a random recipe asset for recipes that are added. */
    fun getRandomRecipeImageAsset(): Int? {
        val randomNumber = (initialRecipeList.indices).random()
        return initialRecipeList[randomNumber].image
    }

    companion object {
        private var INSTANCE: RecipeDataSource? = null

        fun getDataSource(resources: Resources): RecipeDataSource {
            return synchronized(RecipeDataSource::class) {
                val newInstance = INSTANCE ?: RecipeDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}