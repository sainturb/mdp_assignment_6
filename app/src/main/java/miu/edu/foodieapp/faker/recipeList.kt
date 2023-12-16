package miu.edu.foodieapp.faker

import android.content.res.Resources
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.Ingredient
import miu.edu.foodieapp.domain.Recipe
import miu.edu.foodieapp.domain.User
import java.util.Calendar
import java.util.Random

fun recipeList(resources: Resources): List<Recipe> {
    return listOf(
        Recipe(
            id = 1,
            name = "Polygonaceae",
            image = R.drawable.wallemart,
            instruction = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.",
            ingredients = mutableListOf<Ingredient>(
                Ingredient(Random().nextInt(), "Asteraceae", 6, "TBSP"),
                Ingredient(Random().nextInt(), "Cornaceae", 3, "PINT"),
                Ingredient(Random().nextInt(), "Fabaceae", 2, "CUP"),
                Ingredient(Random().nextInt(), "Cactaceae", 4, "CUP"),
                Ingredient(Random().nextInt(), "Polemoniaceae", 1, "G"),
                Ingredient(Random().nextInt(), "Pinaceae", 1, "G")
            ),
            user = User("John","Doe","user1", "pass1", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            cookingTime = 20,
            rating = 4.0
        ),
        Recipe(
            id = 2,
            name = "Ericaceae",
            image = R.drawable.wallemart,
            instruction = "Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.",
            ingredients = mutableListOf<Ingredient>(
                Ingredient(Random().nextInt(), "Asteraceae", 6, "TBSP"),
                Ingredient(Random().nextInt(), "Cornaceae", 3, "PINT"),
                Ingredient(Random().nextInt(), "Fabaceae", 2, "CUP"),
                Ingredient(Random().nextInt(), "Cactaceae", 1, "G")
            ),
            user = User("Howard","Doe","user5", "pass5", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            cookingTime = 152,
            rating = 4.3
        ),
        Recipe(
            id = 3,
            name = "Solanaceae",
            image = R.drawable.wallemart,
            instruction = "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.",
            ingredients = mutableListOf<Ingredient>(
                Ingredient(Random().nextInt(), "Asteraceae", 6, "TBSP"),
                Ingredient(Random().nextInt(), "Cornaceae", 3, "PINT"),
                Ingredient(Random().nextInt(), "Fabaceae", 2, "CUP"),
                Ingredient(Random().nextInt(), "Cactaceae", 4, "CUP"),
                Ingredient(Random().nextInt(), "Polemoniaceae", 1, "G"),
                Ingredient(Random().nextInt(), "Pinaceae", 1, "G")
            ),
            user = User("John","Doe","user1", "pass1", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            cookingTime = 60,
            rating = 3.7
        ),
        Recipe(
            id = 4,
            name = "Poaceae",
            image = R.drawable.wallemart,
            instruction = "Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.",
            ingredients = mutableListOf<Ingredient>(
                Ingredient(Random().nextInt(), "Asteraceae", 6, "TBSP"),
                Ingredient(Random().nextInt(), "Cornaceae", 3, "PINT"),
                Ingredient(Random().nextInt(), "Brassicaceae", 2, "CUP"),
                Ingredient(Random().nextInt(), "Poaceae", 4, "CUP"),
                Ingredient(Random().nextInt(), "Polemoniaceae", 1, "G"),
                Ingredient(Random().nextInt(), "Cyperaceae", 1, "G")
            ),
            user = User("Jane","Doe","user3", "pass3", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            cookingTime = 180,
            rating = 3.4
        ),

    )
}