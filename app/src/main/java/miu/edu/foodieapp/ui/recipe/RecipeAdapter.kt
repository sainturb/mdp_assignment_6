package miu.edu.foodieapp.ui.recipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.Recipe


class RecipeAdapter(private val onClick: (Recipe) -> Unit) :
    ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback) {

    /* ViewHolder for Recipe, takes in the inflated view and the onClick behavior. */
    class RecipeViewHolder(itemView: View, val onClick: (Recipe) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.name)
        private val instructionView: TextView = itemView.findViewById(R.id.instruction)

        private val by: TextView = itemView.findViewById(R.id.by)
        private val cookingTime: TextView = itemView.findViewById(R.id.cookingTime)
        private val rating: TextView = itemView.findViewById(R.id.rating)
        private var currentRecipe: Recipe? = null
        private val ingredientView: RecyclerView = itemView.findViewById(R.id.ingredientList)

        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        private val cookingTimeImage: ImageView = itemView.findViewById(R.id.cookingTimeImage)
        private val ratingImage: ImageView = itemView.findViewById(R.id.ratingImage)

        init {
            itemView.setOnClickListener {
                currentRecipe?.let {
                    onClick(it)
                }
            }
        }

        /* Bind recipe name and image. */
        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            currentRecipe = recipe

            nameView.text = recipe.name
            by.text = "${recipe.user.fname} ${recipe.user.lname}"
            instructionView.text = recipe.instruction
            cookingTime.text = "${recipe.cookingTime} min"
            rating.text = "${recipe.rating}/5.0"
            profileImage.setImageResource(R.drawable.baseline_account_circle_24)
            cookingTimeImage.setImageResource(R.drawable.baseline_access_time_filled_24)
            ratingImage.setImageResource(R.drawable.baseline_thumb_up_24)

            val adapter = IngredientAdapter(recipe.ingredients.map { ingredient -> "❋ ${ingredient.name} | ${ingredient.amount} ${ingredient.measure}" })
            ingredientView.adapter = adapter
        }

        private fun adapterOnClick(ingredient: String) {

        }
    }



    /* Creates and inflates view and return RecipeViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_view, parent, false)
        return RecipeViewHolder(view, onClick)
    }

    /* Gets current recipe and uses it to bind view. */
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)

    }
}

object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }
}