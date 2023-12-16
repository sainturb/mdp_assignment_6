package miu.edu.foodieapp.utils

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import miu.edu.foodieapp.domain.Blog
import miu.edu.foodieapp.faker.blogList

class BlogDataSource(resources: Resources) {
    private val initialBlogList = blogList(resources)
    private val blogsLiveData = MutableLiveData(initialBlogList)

    /* Adds blog to liveData and posts value. */
    fun addBlog(blog: Blog) {
        val currentList = blogsLiveData.value
        if (currentList == null) {
            blogsLiveData.postValue(listOf(blog))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, blog)
            blogsLiveData.postValue(updatedList)
        }
    }

    /* Removes blog from liveData and posts value. */
    fun removeBlog(blog: Blog) {
        val currentList = blogsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(blog)
            blogsLiveData.postValue(updatedList)
        }
    }

    /* Returns blog given an ID. */
    fun getBlogForId(id: Int): Blog? {
        blogsLiveData.value?.let { list ->
            return list.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getBlogList(): LiveData<List<Blog>> {
        return blogsLiveData
    }

    /* Returns a random blog asset for blogs that are added. */
    fun getRandomBlogImageAsset(): Int? {
        val randomNumber = (initialBlogList.indices).random()
        return initialBlogList[randomNumber].image
    }

    companion object {
        private var INSTANCE: BlogDataSource? = null

        fun getDataSource(resources: Resources): BlogDataSource {
            return synchronized(BlogDataSource::class) {
                val newInstance = INSTANCE ?: BlogDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}