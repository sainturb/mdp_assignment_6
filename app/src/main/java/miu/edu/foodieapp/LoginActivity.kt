package miu.edu.foodieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import miu.edu.foodieapp.domain.User
import miu.edu.foodieapp.ui.social.UserViewModel
import miu.edu.foodieapp.ui.social.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    private val userListViewModel by viewModels<UserViewModel> {
        UserViewModelFactory(this)
    }
    private var loggedUser: User? = null

    private val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val firstname = it.data?.getStringExtra("firstname")
            val lastname = it.data?.getStringExtra("lastname")
            val username = it.data?.getStringExtra("username")
            val password = it.data?.getStringExtra("password")
            val email = it.data?.getStringExtra("email")
            val phone = it.data?.getStringExtra("phone")
            if (!firstname.isNullOrBlank() && !lastname.isNullOrBlank() && !username.isNullOrBlank() && !password.isNullOrBlank() && !email.isNullOrBlank() && !phone.isNullOrBlank()) {
                userListViewModel.insertUser(firstname, lastname, username, password, email, phone)
               Toast.makeText(this, "User $firstname is created successfully.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.signIn)
        val forgot = findViewById<TextView>(R.id.forgotPassword)
        val register = findViewById<TextView>(R.id.register)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            if (username.text.isNotEmpty() && password.text.isNotEmpty()) {
                attemptLogin(username.text.toString(), password.text.toString())
            }
        }

        val users = userListViewModel.dataSource.getUserList().value

        forgot.setOnClickListener {
            val username = findViewById<EditText>(R.id.username)
            if (username.text.isNotEmpty()) {
                val found = users?.find { it.name == username.text.toString() }
                if (found != null) {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Your password is " + found.pass)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                } else {
                    Toast.makeText(this, "User is not existed in system.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill the email field.", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            activityLauncher.launch(intent)
        }
    }

    private fun attemptLogin(username: String, password: String): Boolean {
        val users = userListViewModel.dataSource.getUserList().value
        val found = users?.find { it.name == username && it.pass == password}
        if (found != null) {
            loggedUser = found
            Toast.makeText(this, "Welcome! " + found.name, Toast.LENGTH_SHORT).show()

            val credential = this.getSharedPreferences(
                getString(R.string.preference_token_key), Context.MODE_PRIVATE)

            val editor = credential.edit()
            editor.putString("accessToken", Gson().toJson(found))
            editor.putString("username", found.name)
            editor.putString("firstname", found.fname)
            editor.putString("lastname", found.lname)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", found.name)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show()
        }
        return found != null
    }
}