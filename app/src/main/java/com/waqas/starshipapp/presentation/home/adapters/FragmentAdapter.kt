package com.waqas.starshipapp.presentation.home.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.waqas.starshipapp.presentation.home.fragments.AllItemsFragment
import com.waqas.starshipapp.presentation.home.fragments.FavouriteItemsFragment
import com.waqas.starshipapp.presentation.home.viewmodel.HomeViewModel

class FragmentAdapter(activity: AppCompatActivity, private val viewModel: HomeViewModel): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllItemsFragment(viewModel)
            1 -> FavouriteItemsFragment(viewModel)
            else -> AllItemsFragment(viewModel)
        }
    }

}