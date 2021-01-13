package com.example.hiltcleanarchitecture2.presentation.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltcleanarchitecture2.R
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.presentation.base.Resource
import com.example.hiltcleanarchitecture2.databinding.FavoriteFragmentBinding
import com.example.hiltcleanarchitecture2.presentation.ui.MainViewModel
import com.example.hiltcleanarchitecture2.presentation.utils.show
import com.example.hiltcleanarchitecture2.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.favorite_fragment),
    FavoritesAdapter.OnCocktailClickListener {
    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesAdapter = FavoritesAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FavoriteFragmentBinding.bind(view)

        binding.rvTragosFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragosFavoritos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvTragosFavoritos.adapter = favoritesAdapter

        viewModel.getFavoriteCocktails().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@observe
                    }
                    favoritesAdapter.setCocktailList(result.data)
                }
                is Resource.Failure -> {
                    showToast("An error occurred ${result.exception}")
                }
            }
        }
    }

    override fun onCocktailClick(cocktailEntity: CocktailEntity, position: Int) {
        findNavController().navigate(
            FavoritesFragmentDirections.actionFavoritosFragmentToTragosDetalleFragment(
                cocktailEntity
            )
        )
    }

    override fun onCocktailLongClick(cocktailEntity: CocktailEntity, position: Int) {
        viewModel.deleteFavoriteCocktail(cocktailEntity)
    }
}