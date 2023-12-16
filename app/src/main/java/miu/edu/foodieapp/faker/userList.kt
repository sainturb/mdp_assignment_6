package miu.edu.foodieapp.faker

import android.content.res.Resources
import miu.edu.foodieapp.domain.User

fun userList(resources: Resources): List<User> {
    return listOf(
        User("John", "Doe", "john", "pass1", "john@mail.com", "6461112222"),
        User("Hurley", "Quilt", "hurley", "pass2", "hurley@mail.com", "6461112222"),
        User("Jane", "Doe", "jane", "pass3", "jane@mail.com", "6461112222"),
        User("Micheal", "Jonathon", "micheal", "pass4", "micheal@mail.com", "6461112222"),
        User("Howard", "Wayne", "howard", "pass5", "howard@mail.com", "6461112222")
    )
}