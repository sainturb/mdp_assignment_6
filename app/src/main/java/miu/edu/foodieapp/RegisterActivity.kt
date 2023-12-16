package miu.edu.foodieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val register = findViewById<Button>(R.id.createUser)
        register.setOnClickListener {
            val firstname = findViewById<EditText>(R.id.firstname)
            val lastname = findViewById<EditText>(R.id.lastname)
            val username = findViewById<EditText>(R.id.userName)
            val password = findViewById<EditText>(R.id.userPassword)
            val email = findViewById<EditText>(R.id.userEmail)
            val phone = findViewById<EditText>(R.id.userPhone)
            attemptCreate(firstname.text.toString(), lastname.text.toString(), username.text.toString(), password.text.toString(), email.text.toString(), phone.text.toString())
        }

        val login = findViewById<Button>(R.id.login_back)
        login.setOnClickListener {
            goBackToLogin()
        }
    }

    private fun attemptCreate(firstname: String, lastname: String, username: String, password: String, email: String, phone: String) {
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            val data = intent
            data.putExtra("firstname", firstname)
            data.putExtra("lastname", lastname)
            data.putExtra("username", username)
            data.putExtra("password", password)
            data.putExtra("email", email)
            data.putExtra("phone", phone)
            setResult(RESULT_OK, data)
            finish()
        } else {
            Toast.makeText(this, "Fill out the missing fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goBackToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}