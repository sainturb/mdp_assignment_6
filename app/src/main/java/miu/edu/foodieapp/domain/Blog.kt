package miu.edu.foodieapp.domain

import java.util.Date

class Blog(id: Int, title: String, image: Int, content: String, tags: List<String>, user: User, date: Date, likes: Int) {
    var id: Int = id
    var title: String = title
    val image: Int = image
    val content: String = content
    val user: User = user
    val tags: List<String> = tags
    val createdDate: Date = date
    val likes: Int = likes

    override fun toString(): String {
        return "$title $createdDate $likes ${tags.size} ${user.name} ${createdDate}"
    }
}