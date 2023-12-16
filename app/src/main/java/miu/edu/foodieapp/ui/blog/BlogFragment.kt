package miu.edu.foodieapp.ui.blog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import miu.edu.foodieapp.R
import miu.edu.foodieapp.databinding.FragmentBlogBinding
import miu.edu.foodieapp.domain.Blog
import miu.edu.foodieapp.domain.User

class BlogFragment : Fragment() {

    private var _binding: FragmentBlogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBlogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val that = root.context

        val blogListViewModel by viewModels<BlogViewModel> {
            BlogViewModelFactory(that)
        }

        val adapter = BlogAdapter { blog -> adapterOnClick(blog) }

        val recyclerView: RecyclerView = binding.blogList
        recyclerView.adapter = adapter
        blogListViewModel.blogsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it as MutableList<Blog>)
            }
        }

        val addButton: FloatingActionButton = binding.blogPlus

        addButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflaterDialog = layoutInflater
            builder.setTitle("Create a new Blog")
            builder.setMessage("Write what is on your mind")
            val dialogLayout = inflaterDialog.inflate(R.layout.blog_add, null)
            builder.setView(dialogLayout)

            val titleText  = dialogLayout.findViewById<EditText>(R.id.formTitle)
            val contentText  = dialogLayout.findViewById<EditText>(R.id.formContent)

            var tags = mutableListOf<String>()
            val addTagButton = dialogLayout.findViewById<Button>(R.id.addTag)
            addTagButton.setOnClickListener {
                val tagText  = dialogLayout.findViewById<EditText>(R.id.formTag)
                tags.add(tagText.text.toString())
                tagText.text.clear()

//                val adapterIngredients = IngredientAdapter(ingredients.map { ingredient -> "❋ ${ingredient.name} | ${ingredient.amount} ${ingredient.measure}" })
//                ingredientList.adapter = adapterIngredients
            }
            builder.setPositiveButton("Create") { _, _ ->
                val credential = binding.root.context.getSharedPreferences(
                    getString(R.string.preference_token_key), Context.MODE_PRIVATE)
                val token = credential.getString("accessToken", "")
                val user = Gson().fromJson(token, User::class.java)
                val success = blogListViewModel.insertBlog(titleText.text.toString(), contentText.text.toString(), tags, user, 0)
                if (success) {
                    titleText.text.clear()
                    contentText.text.clear()
                    tags = mutableListOf<String>()
                    Toast.makeText(requireContext(), "Blog is successfully added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Fill out required fields before you create a blog", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        return root
    }

    private fun adapterOnClick(blog: Blog) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}