package miu.edu.foodieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import miu.edu.foodieapp.databinding.ActivityMainBinding
import miu.edu.foodieapp.ui.blog.BlogFragment
import miu.edu.foodieapp.ui.planner.PlannerFragment
import miu.edu.foodieapp.ui.profile.ProfileFragment
import miu.edu.foodieapp.ui.recipe.RecipesFragment
import miu.edu.foodieapp.ui.social.SocialFragment

class MainActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager2
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val credential = this.getSharedPreferences(
            getString(R.string.preference_token_key), Context.MODE_PRIVATE)

        val token = credential.getString("accessToken", "")
        if (token != null && token.isEmpty()) {
            navigateToLogin()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabView
        val navView: BottomNavigationView = binding.navView
        pager = binding.pager

        val fragments = listOf(RecipesFragment(),PlannerFragment(),BlogFragment(),SocialFragment(),ProfileFragment())
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
        pager.adapter = ViewPagerAdapter(fragments, supportFragmentManager)

        navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_recipe -> {
                    pager.setCurrentItem(0, true)
                }
                R.id.navigation_planner -> {
                    pager.setCurrentItem(1, true)
                }
                R.id.navigation_blog -> {
                    pager.setCurrentItem(2, true)
                }
                else -> {
                }
            }
            return@setOnItemSelectedListener true
        }

        val titles = mutableListOf("Recipes", "Planner", "Blog", "Social", "Profile")
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = (titles[position])
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "Recipes" -> {
                        pager.setCurrentItem(0, true)
                    }
                    "Planner" -> {
                        pager.setCurrentItem(1, true)
                    }
                    "Blog" -> {
                        pager.setCurrentItem(2, true)
                    }
                    "Social" -> {
                        pager.setCurrentItem(3, true)
                    }
                    "Profile" -> {
                        pager.setCurrentItem(4, true)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private inner class ViewPagerAdapter(private val fragments: List<Fragment>, fragmentManager: FragmentManager) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}