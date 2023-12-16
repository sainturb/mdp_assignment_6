package miu.edu.foodieapp.domain

import java.util.Date

class Recipe(id: Int, name: String, image: Int, instruction: String, ingredients: List<Ingredient>, user: User, date: Date, cookingTime: Int, rating: Double) {
    var id: Int = id
    var name: String = name
    val image: Int = image
    val instruction: String = instruction
    val user: User = user
    val ingredients: List<Ingredient> = ingredients
    val createdDate: Date = date
    val cookingTime: Int = cookingTime
    val rating: Double = rating

    override fun toString(): String {
        return "$name $cookingTime $rating ${ingredients.size} ${user.name} ${createdDate}"
    }
}