package miu.edu.foodieapp.ui.blog

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
import miu.edu.foodieapp.domain.Blog


class BlogAdapter(private val onClick: (Blog) -> Unit) :
    ListAdapter<Blog, BlogAdapter.BlogViewHolder>(BlogDiffCallback) {

    /* ViewHolder for Blog, takes in the inflated view and the onClick behavior. */
    class BlogViewHolder(itemView: View, val onClick: (Blog) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.title)
        private val dateView: TextView = itemView.findViewById(R.id.createdDate)
        private val contentView: TextView = itemView.findViewById(R.id.content)

        private val by: TextView = itemView.findViewById(R.id.by)
        private val likes: TextView = itemView.findViewById(R.id.likes)
        private var currentBlog: Blog? = null
//        private val tagView: RecyclerView = itemView.findViewById(R.id.tagList)

        private val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        private val likesImage: ImageView = itemView.findViewById(R.id.likesImage)

        init {
            itemView.setOnClickListener {
                currentBlog?.let {
                    onClick(it)
                }
            }
        }

        /* Bind blog name and image. */
        @SuppressLint("SetTextI18n")
        fun bind(blog: Blog) {
            currentBlog = blog

            titleView.text = blog.title
            by.text = "${blog.user.fname} ${blog.user.lname}"
            contentView.text = blog.content
            likes.text = "${blog.likes}"
            profileImage.setImageResource(R.drawable.baseline_account_circle_24)
            likesImage.setImageResource(R.drawable.baseline_thumb_up_24)
            dateView.text = blog.createdDate.toString()
//            val adapter = TagAdapter(blog.ingredients.map { ingredient -> "❋ ${ingredient.name} | ${ingredient.amount} ${ingredient.measure}" })
//            ingredientView.adapter = adapter
        }

        private fun adapterOnClick(ingredient: String) {

        }
    }



    /* Creates and inflates view and return BlogViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.blog_view, parent, false)
        return BlogViewHolder(view, onClick)
    }

    /* Gets current blog and uses it to bind view. */
    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = getItem(position)
        holder.bind(blog)

    }
}

object BlogDiffCallback : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.id == newItem.id
    }
}