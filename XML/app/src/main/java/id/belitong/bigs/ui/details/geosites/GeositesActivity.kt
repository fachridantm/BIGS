package id.belitong.bigs.ui.details.geosites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.ui.CardGeositeAdapter
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.ActivityGeositesBinding
import id.belitong.bigs.ui.main.MainViewModel

@AndroidEntryPoint
class GeositesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeositesBinding
    private val cardGeositeAdapter: CardGeositeAdapter by lazy { CardGeositeAdapter(::onItemClick) }

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var data: List<Geosite>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeositesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initAction()
        initObservers()
    }

    private fun initData() {
        mainViewModel.apply {
            getGeosites()
        }
    }

    private fun initView() {
        with(binding) {
            rvGeosites.apply {
                adapter = cardGeositeAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun initAction() {
        with(binding) {
            chipAlphabet.setOnClickListener {
                val alphabetSort = data.sortedBy { it.name }
                cardGeositeAdapter.submitList(alphabetSort)
                rvGeosites.smoothScrollToPosition(alphabetSort.indexOf(alphabetSort.first()))
            }

            chipNearest.setOnClickListener {
                val nearestSort = data.sortedBy { it.distance }
                cardGeositeAdapter.submitList(nearestSort)
                rvGeosites.smoothScrollToPosition(nearestSort.indexOf(nearestSort.first()))
            }

            toolbarGeosites.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    private fun initObservers() {
        mainViewModel.geosites.observe(this) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    cardGeositeAdapter.submitList(it.data)
                    data = it.data
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(binding.root)
                }

                else -> {}
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                pbGeosites.visibility = View.VISIBLE
            } else {
                pbGeosites.visibility = View.GONE
            }
        }
    }

    private fun onItemClick(geosite: Geosite) {
        getString(R.string.on_click_handler).showSnackbar(binding.root)
    }

    companion object {
        fun start(context: Context) {
            Intent(context, GeositesActivity::class.java).run { context.startActivity(this) }
        }
    }
}