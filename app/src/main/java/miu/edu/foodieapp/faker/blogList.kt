package miu.edu.foodieapp.faker

import android.content.res.Resources
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.Blog
import miu.edu.foodieapp.domain.User
import java.util.Calendar

fun blogList(resources: Resources): List<Blog> {
    return listOf(
        Blog(
            id = 1,
            title = "Solanaceae",
            image = R.drawable.wallemart,
            content = "Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.",
            tags =mutableListOf<String>("name"),
            user = User("John","Doe","user1", "pass1", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            likes = 9,
        ),
        Blog(
            id = 2,
            title ="Poaceae",
            image = R.drawable.wallemart,
            content = "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.",
            tags = mutableListOf<String>(),
            user = User("Howard","Doe","user5", "pass5", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            likes = 6,
        ),
        Blog(
            id = 3,
            title ="Polygonaceae",
            image = R.drawable.wallemart,
            content = "Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.",
            tags = mutableListOf<String>(),
            user = User("John","Doe","user1", "pass1", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            likes = 3
        ),
        Blog(
            id = 4,
            title ="Ericaceae",
            image = R.drawable.wallemart,
            content = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.",
            tags =mutableListOf<String>(),
            user = User("Jane","Doe","user3", "pass3", "mail@mail.com", "6461112222"),
            date = Calendar.getInstance().time,
            likes = 2,
        ),

    )
}