package com.example.bottomnavigationpractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.bottomnavigationpractice.fragment.DashboardFragment
import com.example.bottomnavigationpractice.fragment.HomeFragment
import com.example.bottomnavigationpractice.fragment.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeFragment.HomeButtonListener {
    object LogicValues {
        const val HOME_FRAGMENT = 0
        const val DASHBOARD_FRAGMENT = 1
        const val NOTIFICATION_FRAGMENT = 2
    }

    private var currentItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupViewPager()

        nav_view.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    view_pager.setCurrentItem(LogicValues.HOME_FRAGMENT, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    view_pager.setCurrentItem(LogicValues.DASHBOARD_FRAGMENT, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    view_pager.setCurrentItem(LogicValues.NOTIFICATION_FRAGMENT, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is HomeFragment) {
            fragment.callback = this
        }
    }

    override fun onClickedListener() {
        view_pager.setCurrentItem(LogicValues.DASHBOARD_FRAGMENT, false)
    }

    private fun setupViewPager() {
        val fragmentList = mutableListOf(HomeFragment(), DashboardFragment(), NotificationsFragment())
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 2
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                this@MainActivity.currentItem?.let {
                    it.isChecked = false
                } ?: run {
                    nav_view.menu.getItem(0).isChecked = false
                }

                nav_view.menu.getItem(position).isChecked = true
                this@MainActivity.currentItem = nav_view.menu.getItem(position)
            }

        })
    }
}

class ViewPagerAdapter(
    private val fragment: List<Fragment>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = fragment[position]

    override fun getCount() = this.fragment.size
}

class BottomNavigationViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    fun changeCheckItem(position: Int) {
        this.currentItem
    }
}
