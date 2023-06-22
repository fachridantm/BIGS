package id.belitong.bigs.ui.search

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.ui.CardSearchAdapter
import id.belitong.bigs.core.utils.DummyData
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.ActivitySearchResultsBinding

@AndroidEntryPoint
class SearchResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultsBinding
    private val cardSearchAdapter: CardSearchAdapter by lazy { CardSearchAdapter(::onItemClick) }
    private val searchResultsViewModel: SearchResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initAction()
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

            val data = DummyData.getAllBiodiversity()

            chipGeositesSearch.setOnClickListener {
                val geosites = data.filter {
                    it.type != getString(R.string.plant) && it.type != getString(R.string.animal)
                }
                cardSearchAdapter.submitList(geosites)
            }

            chipPlantsSearch.setOnClickListener {
                val plants = data.filter { it.type == getString(R.string.plant) }
                cardSearchAdapter.submitList(plants)
            }

            chipAnimalsSearch.setOnClickListener {
                val animals = data.filter { it.type == getString(R.string.animal) }
                cardSearchAdapter.submitList(animals)
            }

            chipLocationSearch.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(this@SearchResultsActivity)
                cardSearchAdapter.submitList(emptyList())
            }

            toolbarSearch.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }
            })
        }
    }

    private fun onItemClick() {
        getString(R.string.onClickHandler).showMessage(this)
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.requestFocus()
    }
}