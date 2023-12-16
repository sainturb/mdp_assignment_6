package miu.edu.foodieapp.ui.recipe

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import miu.edu.foodieapp.R
import miu.edu.foodieapp.databinding.FragmentRecipesBinding
import miu.edu.foodieapp.domain.Ingredient
import miu.edu.foodieapp.domain.Recipe
import miu.edu.foodieapp.domain.User
import kotlin.random.Random

class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val that = root.context

        val recipeListViewModel by viewModels<RecipesViewModel> {
            RecipesViewModelFactory(that)
        }

        val adapter = RecipeAdapter { recipe -> adapterOnClick(recipe) }

        val recyclerView: RecyclerView = binding.recipeList
        recyclerView.adapter = adapter
        recipeListViewModel.recipesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it as MutableList<Recipe>)
            }
        }

        val addButton: FloatingActionButton = binding.recipePlus

        addButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflaterDialog = layoutInflater
            builder.setTitle("Add a new Recipe")
            builder.setMessage("Fill out the form")
            val dialogLayout = inflaterDialog.inflate(R.layout.recipe_add, null)
            builder.setView(dialogLayout)

            val nameText  = dialogLayout.findViewById<EditText>(R.id.formName)
            val instructionText  = dialogLayout.findViewById<EditText>(R.id.formInstruction)
            val cookingTime  = dialogLayout.findViewById<EditText>(R.id.formCookingTime)
            val ingredientList = dialogLayout.findViewById<RecyclerView>(R.id.ingredientList)
            val measureSpinner  = dialogLayout.findViewById<Spinner>(R.id.formMeasure)

            val measures = resources.getStringArray(R.array.Measures)
            if (measureSpinner != null) {
                val adapterMeasures = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    measures
                )
                measureSpinner.adapter = adapterMeasures
            }


            var ingredients = mutableListOf<Ingredient>()
            val addIngredientButton = dialogLayout.findViewById<Button>(R.id.addIngredient)
            addIngredientButton.setOnClickListener {
                val ingredientText  = dialogLayout.findViewById<EditText>(R.id.formIngredient)
                val amountText  = dialogLayout.findViewById<EditText>(R.id.formAmount)
                val newIngredient = Ingredient(Random.nextInt(), ingredientText.text.toString(), amountText.text.toString().toInt(), measures[measureSpinner.selectedItemPosition])
                ingredients.add(newIngredient)
                println(newIngredient.toString())
                ingredientText.text.clear()
                amountText.text.clear()
                measureSpinner.setSelection(0)

                val adapterIngredients = IngredientAdapter(ingredients.map { ingredient -> "❋ ${ingredient.name} | ${ingredient.amount} ${ingredient.measure}" })
                ingredientList.adapter = adapterIngredients
            }
            builder.setPositiveButton("Add") { _, _ ->
                val ct = if (cookingTime.text.isEmpty()) null else cookingTime.text.toString().toInt()
                val credential = binding.root.context.getSharedPreferences(
                    getString(R.string.preference_token_key), Context.MODE_PRIVATE)
                val token = credential.getString("accessToken", "")
                val user = Gson().fromJson(token, User::class.java)
                val success = recipeListViewModel.insertRecipe(nameText.text.toString(), instructionText.text.toString(), ingredients, user, ct)
                if (success) {
                    nameText.text.clear()
                    instructionText.text.clear()
                    cookingTime.text.clear()
                    ingredients = mutableListOf<Ingredient>()
                    Toast.makeText(requireContext(), "Recipe is successfully added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Fill out required fields before you add recipe", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        return root
    }

    private fun adapterOnClick(recipe: Recipe) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

