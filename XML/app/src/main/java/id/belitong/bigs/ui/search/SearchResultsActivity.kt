package id.belitong.bigs.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.ui.CardSearchAdapter
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.ActivitySearchResultsBinding

@AndroidEntryPoint
class SearchResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultsBinding

    private val cardSearchAdapter: CardSearchAdapter by lazy { CardSearchAdapter(::onItemClick) }
    private val searchResultsViewModel: SearchResultsViewModel by viewModels()

    private var biodiversities: List<Biodiversity> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initAction()
        initObservers()
    }

    private fun initData() {
        searchResultsViewModel.apply {
            getBiodiversities()
        }
    }

    private fun initObservers() {
        searchResultsViewModel.biodiversities.observe(this) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    biodiversities = it.data

                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(binding.root)
                }

                else -> {}
            }
        }
    }

    private fun initView() {
        with(binding) {
            searchView.requestFocus()
            rvSearchResults.apply {
                adapter = cardSearchAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun initAction() {
        with(binding) {
            chipGeositesSearch.setOnClickListener {
                val geosites = biodiversities.filter {
                    it.type != getString(R.string.plant) && it.type != getString(R.string.animal)
                }
                cardSearchAdapter.submitList(geosites)
            }

            chipPlantsSearch.setOnClickListener {
                val plants = biodiversities.filter { it.type == getString(R.string.plant) }
                cardSearchAdapter.submitList(plants)
            }

            chipAnimalsSearch.setOnClickListener {
                val animals = biodiversities.filter { it.type == getString(R.string.animal) }
                cardSearchAdapter.submitList(animals)
            }

            chipLocationSearch.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(binding.root)
                cardSearchAdapter.submitList(emptyList())
            }

            toolbarSearch.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false
                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }
    }

    private fun onItemClick(biodiversity: Biodiversity) {
        getString(R.string.on_click_handler).showSnackbar(binding.root)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                binding.pbSearchResults.visibility = View.VISIBLE
            } else {
                binding.pbSearchResults.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.requestFocus()
    }

    companion object {
        fun start(context: Context) {
            Intent(context, SearchResultsActivity::class.java).run { context.startActivity(this) }
        }
    }
}