package id.belitong.bigs.ui.details.geosites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.ui.CardGeositeAdapter
import id.belitong.bigs.core.utils.DummyData
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.ActivityGeositesBinding

@AndroidEntryPoint
class GeositesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeositesBinding
    private val cardGeositeAdapter: CardGeositeAdapter by lazy { CardGeositeAdapter(::onItemClick) }
    private val data = DummyData.getAllGeosites()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeositesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initAction()
    }

    private fun initData() {
        cardGeositeAdapter.submitList(data)
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
                rvGeosites.smoothScrollToPosition(0)
            }

            chipNearest.setOnClickListener {
                val nearestSort = data.sortedBy { it.distance }
                cardGeositeAdapter.submitList(nearestSort)
                rvGeosites.smoothScrollToPosition(0)
            }

            toolbarGeosites.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    private fun onItemClick(geosite: Geosite) {
        getString(R.string.on_click_handler).showMessage(this)
    }
}