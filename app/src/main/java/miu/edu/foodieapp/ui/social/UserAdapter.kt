package miu.edu.foodieapp.ui.social

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.User


class UserAdapter(private val onClick: (User, String) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback) {

    /* ViewHolder for User, takes in the inflated view and the onClick behavior. */
    class UserViewHolder(itemView: View, val onClick: (User, String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.name)
        private var currentUser: User? = null

        private val phoneView: Button = itemView.findViewById(R.id.phoneCall)
        private val emailView: Button = itemView.findViewById(R.id.emailContact)

        init {
            phoneView.setOnClickListener {
                currentUser?.let {
                    onClick(it, "phone")
                }
            }

            emailView.setOnClickListener {
                currentUser?.let {
                    onClick(it, "email")
                }
            }
        }

        /* Bind user name and image. */
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            currentUser = user
            nameView.text = "${user.fname} ${user.lname}"
        }
    }



    /* Creates and inflates view and return UserViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.social_view, parent, false)
        return UserViewHolder(view, onClick)
    }

    /* Gets current user and uses it to bind view. */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }
}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name
    }
}