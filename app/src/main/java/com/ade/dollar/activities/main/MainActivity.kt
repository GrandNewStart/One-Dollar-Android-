package com.ade.dollar.activities.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import com.ade.dollar.config.Constants
import com.ade.dollar.R
import com.ade.dollar.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initActionBarLayout()
        initRefreshLayout()
        initMenu()
        setContentView(binding.root)
        viewModel.apply {
            isLoading.observe(this@MainActivity) {
                binding.refreshLayout.isRefreshing = it
            }
            price.observe(this@MainActivity) {
                binding.wonTextView.text = it
            }
            date.observe(this@MainActivity) {
                it?.let {
                    val date = "$it ${getString(R.string.based)}"
                    binding.dateTextView.text = date
                }
            }
            getPrice()
        }
    }

    private fun initActionBarLayout() {
        val orientation = resources.configuration.orientation
        val height = Constants.dp2px(this, if (orientation == Configuration.ORIENTATION_PORTRAIT) 300 else 200  )
        binding.appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener  { _, verticalOffset ->
                val percent = abs(verticalOffset)/height.toFloat()
                adjustTitleImage(percent)
                adjustTitleTextView(percent)
                adjustMenuButton(percent)
            }
        )
    }

    private fun adjustTitleImage(percent: Float) {
        val width = resources.displayMetrics.widthPixels
        val scale = 1 - percent
        val xOffset = -percent * width * 0.5f
        val alpha = ((1 - percent * 2) * 255).toInt()
        binding.moneyImageView.apply {
            scaleX = scale
            scaleY = scale
            translationY = -xOffset
            imageAlpha = if (alpha < 25) 0 else alpha
        }
    }

    private fun adjustTitleTextView(percent: Float) {
        val scale = -percent * 0.5f + 1
        binding.titleTextView.apply {
            scaleX = scale
            scaleY = scale
        }
    }

    private fun adjustMenuButton(percent: Float) {
        binding.menuButton.apply {
            isVisible = percent > 0.5f
        }
    }

    private fun initRefreshLayout() {
        binding.refreshLayout.apply {
            setOnRefreshListener {
                viewModel.price.value = null
                viewModel.getPrice()
            }
        }
    }

    private fun initMenu() {
        binding.menuButton.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayout.closeDrawers()
            }
            else {
                binding.drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            var handled = false
            when (menu.itemId) {
                R.id.menu_notification -> {
                    binding.drawerLayout.closeDrawers()
                    handled = true
                }
                R.id.menu_price -> {
                    binding.drawerLayout.closeDrawers()
                    handled = true
                }
                R.id.menu_version -> {
                    binding.drawerLayout.closeDrawers()
                    handled = true
                }
            }
            handled
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            binding.drawerLayout.closeDrawers()
            return
        }
        super.onBackPressed()
    }

}