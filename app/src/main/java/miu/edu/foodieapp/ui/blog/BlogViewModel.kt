package miu.edu.foodieapp.ui.blog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import miu.edu.foodieapp.R
import miu.edu.foodieapp.domain.Blog
import miu.edu.foodieapp.domain.User
import miu.edu.foodieapp.utils.BlogDataSource
import java.util.Calendar
import kotlin.random.Random

class BlogViewModel(val dataSource: BlogDataSource) : ViewModel(){

    val blogsLiveData = dataSource.getBlogList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertBlog(title: String?, content: String?, tags: List<String>, user: User, likes: Int?): Boolean {
        if (title == null || content == null || tags.isEmpty()) {
            return false
        }

        val blog = Blog(
            Random.nextInt(),
            title,
            R.drawable.wallemart,
            content,
            tags,
            user,
            Calendar.getInstance().time,
            likes ?: 0
        )
        println(blog.toString())
        dataSource.addBlog(blog)
        return true
    }
}

class BlogViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BlogViewModel(
                dataSource = BlogDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}