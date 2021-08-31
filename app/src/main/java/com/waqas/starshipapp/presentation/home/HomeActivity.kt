package com.waqas.starshipapp.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.tabs.TabLayoutMediator
import com.waqas.starshipapp.R
import com.waqas.starshipapp.databinding.ActivityHomeBinding
import com.waqas.starshipapp.presentation.home.adapters.FragmentAdapter
import com.waqas.starshipapp.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        getStarships()
    }
    private fun setupUI(){
        binding.homeViewPager.adapter = FragmentAdapter(this,viewModel)

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = getString(R.string.all_fragment_title)
                    tab.icon = AppCompatResources.getDrawable(this,R.drawable.all_items)
                }
                1 -> {
                    tab.text = getString(R.string.favourite_fragment_title)
                    tab.icon = AppCompatResources.getDrawable(this,R.drawable.favourite_items)
                }
            }
        }.attach()
    }

    private fun getStarships(){
        viewModel.getStarships()
    }
}