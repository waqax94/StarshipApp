package com.waqas.starshipapp.presentation.home.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.waqas.starshipapp.R
import com.waqas.starshipapp.domain.home.entity.StarshipEntity
import com.waqas.starshipapp.presentation.home.viewmodel.HomeViewModel

class RecyclerViewAdapter(private val context: Context, private val viewModel: HomeViewModel, private val favourites: Boolean) :
RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder>(){

    private var starships: List<StarshipEntity> = ArrayList()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val starshipName: TextView = itemView.findViewById(R.id.item_name)
        val starshipClass: TextView = itemView.findViewById(R.id.list_item_class)
        val favouriteBtn: ImageButton = itemView.findViewById(R.id.item_favorite_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.starship_list_item,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = starships[position]

        holder.starshipName.text = currentItem.name
        holder.starshipClass.text = currentItem.starshipClass

        setFavouriteBtnUI(holder,currentItem)

        holder.favouriteBtn.setOnClickListener {
            viewModel.setFavourite(currentItem.name,!currentItem.isFavourite)
            setStarshipList(viewModel.getUpdatedList())
            if(!currentItem.isFavourite){
                Toast.makeText(context,context.getString(R.string.item_removed), Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,context.getString(R.string.item_added), Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun getItemCount(): Int {
        return starships.size
    }

    fun setStarshipList(list: List<StarshipEntity>){
        this.starships = ArrayList()
        val favs = mutableListOf<StarshipEntity>()
        if(favourites){
            list.forEach{
                if(it.isFavourite){
                    favs.add(it)
                }
            }
            this.starships = favs
        }
        else{
            this.starships = list
        }
        notifyDataSetChanged()
    }

    private fun setFavouriteBtnUI(holder: ListViewHolder, currentItem: StarshipEntity){
        if(currentItem.isFavourite){
            holder.favouriteBtn.setImageDrawable(ResourcesCompat.getDrawable(context.resources,R.drawable.star_filled,null))
        }
        else{
            holder.favouriteBtn.setImageDrawable(ResourcesCompat.getDrawable(context.resources,R.drawable.star_empty,null))
        }
    }
}