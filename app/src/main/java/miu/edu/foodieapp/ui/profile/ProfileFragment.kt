package miu.edu.foodieapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import miu.edu.foodieapp.LoginActivity
import miu.edu.foodieapp.R
import miu.edu.foodieapp.databinding.FragmentProfileBinding
import miu.edu.foodieapp.domain.User

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val name: TextView = binding.textProfile
        val uname: TextView = binding.textUsername

        val logout: Button = binding.logout

        val credential = binding.root.context.getSharedPreferences(
            getString(R.string.preference_token_key), Context.MODE_PRIVATE)

        val token = credential.getString("accessToken", "")

        logout.setOnClickListener {
            credential.edit().remove("accessToken").apply()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        };

        name.text = "it"
        val user = Gson().fromJson(token, User::class.java)
        if (user != null) {
            name.text = "${user.fname} ${user.lname}"
            uname.text = "@${user.name}"
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}