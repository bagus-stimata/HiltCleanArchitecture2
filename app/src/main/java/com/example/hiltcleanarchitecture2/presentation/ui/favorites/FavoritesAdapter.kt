package com.example.hiltcleanarchitecture2.presentation.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiltcleanarchitecture2.presentation.base.BaseViewHolder
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.databinding.TragosRowBinding

/**
 * Created by Gastón Saillén on 08 August 2020
 */
class FavoritesAdapter(
    private val context: Context,
    private val itemClickListener: OnCocktailClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var cocktailList = listOf<CocktailEntity>()

    interface OnCocktailClickListener {
        fun onCocktailClick(cocktailEntity: CocktailEntity, position: Int)

        fun onCocktailLongClick(cocktailEntity: CocktailEntity, position: Int)
    }

    fun setCocktailList(cocktailEntityList: List<CocktailEntity>) {
        this.cocktailList = cocktailEntityList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = TragosRowBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?: return@setOnClickListener

            itemClickListener.onCocktailClick(cocktailList[position], position)
        }

        holder.itemView.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?: return@setOnLongClickListener true

            itemClickListener.onCocktailLongClick(cocktailList[position], position)

            return@setOnLongClickListener true
        }

        return holder
    }

    override fun getItemCount(): Int = cocktailList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cocktailList[position])
        }
    }

    private inner class MainViewHolder(
        private val binding: TragosRowBinding
    ) : BaseViewHolder<CocktailEntity>(binding.root) {
        override fun bind(item: CocktailEntity) = with(binding) {
            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(imgCocktail)

            txtTitulo.text = item.name
            txtDescripcion.text = item.description
        }
    }
}