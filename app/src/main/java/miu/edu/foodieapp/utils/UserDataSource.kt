package miu.edu.foodieapp.utils

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import miu.edu.foodieapp.domain.User
import miu.edu.foodieapp.faker.userList

class UserDataSource(resources: Resources) {
    private val initialUserList = userList(resources)
    private val usersLiveData = MutableLiveData(initialUserList)

    /* Adds user to liveData and posts value. */
    fun addUser(user: User) {
        val currentList = usersLiveData.value
        if (currentList == null) {
            usersLiveData.postValue(listOf(user))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, user)
            usersLiveData.postValue(updatedList)
        }
    }

    /* Removes user from liveData and posts value. */
    fun removeUser(user: User) {
        val currentList = usersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(user)
            usersLiveData.postValue(updatedList)
        }
    }

    fun getUserList(): LiveData<List<User>> {
        return usersLiveData
    }

    companion object {
        private var INSTANCE: UserDataSource? = null

        fun getDataSource(resources: Resources): UserDataSource {
            return synchronized(UserDataSource::class) {
                val newInstance = INSTANCE ?: UserDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}