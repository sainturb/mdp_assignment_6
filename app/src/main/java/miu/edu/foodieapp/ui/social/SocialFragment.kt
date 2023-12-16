package miu.edu.foodieapp.ui.social

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import miu.edu.foodieapp.databinding.FragmentSocialBinding
import miu.edu.foodieapp.domain.User

class SocialFragment : Fragment() {

    private var _binding: FragmentSocialBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val that = root.context

        val userListViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(that)
        }

        val adapter = UserAdapter { user, type -> adapterOnClick(user, type) }

        val recyclerView: RecyclerView = binding.userList
        recyclerView.adapter = adapter

        userListViewModel.userLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it as MutableList<User>)
            }
        }

        return root
    }

    private fun adapterOnClick(user: User, type: String) {
        println(type)
        if (type === "phone") {
            val intent = Intent(Intent.ACTION_CALL);
            intent.data = Uri.parse("tel:${user.phone}")
            try {
                startActivity(intent)
            } catch (e: Exception){
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }

        if (type === "email") {
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello, ")
            try {
                startActivity(intent)
            }
            catch (e: Exception){
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}