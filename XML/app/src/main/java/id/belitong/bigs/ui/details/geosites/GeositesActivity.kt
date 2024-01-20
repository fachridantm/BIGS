package id.belitong.bigs.ui.details.geosites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.ui.CardGeositeAdapter
import id.belitong.bigs.core.utils.parcelableArrayList
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.core.utils.toArrayList
import id.belitong.bigs.databinding.ActivityGeositesBinding

@AndroidEntryPoint
class GeositesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeositesBinding
    private val cardGeositeAdapter: CardGeositeAdapter by lazy { CardGeositeAdapter(::onItemClick) }

    private val geosites by lazy { intent.parcelableArrayList<Geosite>(EXTRA_DATA) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeositesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initObservers()
        initView()
        initAction()
    }

    private fun initData() {
        cardGeositeAdapter.submitList(geosites)
    }

    private fun initObservers() {}

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
            if (!geosites.isNullOrEmpty()) {
                val alphabetSort = geosites?.sortedBy { it.name }.orEmpty()
                val nearestSort = geosites?.sortedBy { it.distance }.orEmpty()

                val dataObserver = object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        rvGeosites.smoothScrollToPosition(0)
                    }
                }

                cardGeositeAdapter.registerAdapterDataObserver(dataObserver)

                chipAlphabet.setOnClickListener {
                    cardGeositeAdapter.submitList(alphabetSort)
                    rvGeosites.post {
                        rvGeosites.smoothScrollToPosition(0)
                    }
                }

                chipNearest.setOnClickListener {
                    cardGeositeAdapter.submitList(nearestSort)
                    rvGeosites.post {
                        rvGeosites.smoothScrollToPosition(0)
                    }
                }

                toolbarGeosites.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
            }
        }
    }

    private fun onItemClick(geosite: Geosite) {
        getString(R.string.on_click_handler).showSnackbar(binding.root)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        fun start(context: Context, geosites: List<Geosite>) {
            Intent(context, GeositesActivity::class.java).apply {
                putParcelableArrayListExtra(EXTRA_DATA, geosites.toArrayList())
            }.run { context.startActivity(this) }
        }
    }
}