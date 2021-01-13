package com.example.hiltcleanarchitecture2.presentation.ui.cocktaildetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.hiltcleanarchitecture2.R
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.databinding.FragmentCocktailDetailsBinding
import com.example.hiltcleanarchitecture2.presentation.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment(R.layout.fragment_cocktail_details) {
    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var cocktailEntity: CocktailEntity

    private var isCocktailFavorited: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            CocktailDetailsFragmentArgs.fromBundle(it).also { args ->
                cocktailEntity = args.drink
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCocktailDetailsBinding.bind(view)

        Glide.with(requireContext())
            .load(cocktailEntity.image)
            .centerCrop()
            .into(binding.imgCocktail)

        binding.cocktailTitle.text = cocktailEntity.name
        binding.cocktailDesc.text = cocktailEntity.description

        fun updateButtonIcon() {
            val isCocktailFavorited = isCocktailFavorited ?: return

            binding.btnSaveOrDeleteCocktail.setImageResource(
                when {
                    isCocktailFavorited -> R.drawable.ic_baseline_delete_24
                    else -> R.drawable.ic_baseline_save_24
                }
            )
        }

        binding.btnSaveOrDeleteCocktail.setOnClickListener {
            val isCocktailFavorited = isCocktailFavorited ?: return@setOnClickListener

            viewModel.saveOrDeleteFavoriteCocktail(cocktailEntity)
            this.isCocktailFavorited = !isCocktailFavorited
            updateButtonIcon()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            isCocktailFavorited = viewModel.isCocktailFavorite(cocktailEntity)
            updateButtonIcon()
        }
    }
}