package miu.edu.foodieapp.ui.social

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import miu.edu.foodieapp.domain.User
import miu.edu.foodieapp.utils.UserDataSource

class UserViewModel(val dataSource: UserDataSource) : ViewModel() {

    val userLiveData = dataSource.getUserList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertUser(firstname: String, lastname: String, username: String, password: String, email: String, phone: String): Boolean {
        if (firstname == null || lastname == null) {
            return false
        }

        val user = User(
            firstname,
                    lastname,
                    username,
            password,
            email,
            phone
        )
        println(user.toString())
        dataSource.addUser(user)
        return true
    }
}

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(
                dataSource = UserDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}