package com.waqas.starshipapp.presentation.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.waqas.starshipapp.R
import com.waqas.starshipapp.databinding.FragmentFavouriteItemsBinding
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import com.waqas.starshipapp.presentation.home.adapters.RecyclerViewAdapter
import com.waqas.starshipapp.presentation.home.viewmodel.HomeActivityState
import com.waqas.starshipapp.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavouriteItemsFragment(private val viewModel: HomeViewModel) : Fragment() {
    private lateinit var binding: FragmentFavouriteItemsBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouriteItemsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        initRecyclerView()
        observe()
        return binding.root
    }

    override fun onResume() {
        recyclerViewAdapter.setStarshipList(viewModel.getUpdatedList())
        super.onResume()
    }

    private fun initRecyclerView(){
        recyclerViewAdapter = RecyclerViewAdapter(binding.root.context,viewModel,true)
        val layoutManager = LinearLayoutManager(binding.root.context)
        binding.fragFavItemsRecyclerView.adapter = recyclerViewAdapter
        binding.fragFavItemsRecyclerView.setHasFixedSize(true)
        binding.fragFavItemsRecyclerView.layoutManager = layoutManager

        binding.fragFavRefreshLayout.setOnRefreshListener {
            viewModel.getStarships()
        }
    }

    private fun observe() {
        viewModel.mState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: HomeActivityState){
        when(state){
            is HomeActivityState.Init -> Unit
            is HomeActivityState.IsLoading -> handleLoading(state.isLoading)
            is HomeActivityState.SuccessResponse -> handleSuccessResponse(state.starships)
            is HomeActivityState.ErrorResponse -> handleErrorResponse()
        }
    }

    private fun handleLoading(isLoading:Boolean){
        if(isLoading){
            binding.fragFavItemsProgress.visibility = View.VISIBLE
            binding.fragFavItemsRecyclerView.visibility = View.GONE
        }
        else{
            binding.fragFavItemsProgress.visibility = View.GONE
            binding.fragFavItemsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun handleSuccessResponse(starships : List<StarshipEntity>){
        binding.fragFavRefreshLayout.isRefreshing = false
        recyclerViewAdapter.setStarshipList(starships)
    }

    private fun handleErrorResponse(){
        binding.fragFavRefreshLayout.isRefreshing = false
        Toast.makeText(binding.root.context,getString(R.string.generic_error), Toast.LENGTH_SHORT).show()
    }
}