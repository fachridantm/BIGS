package id.belitong.bigs.ui.details.geoprogramme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.ActivityGeoprogrammeBinding

@AndroidEntryPoint
class GeoprogrammeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeoprogrammeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeoprogrammeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAction()
    }

    private fun initAction() {
        with(binding) {
            toolbarGeoprogramme.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            btnTourGuide.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(binding.root)
            }

            btnCommunity.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(binding.root)
            }

            btnLocalResident.setOnClickListener {
                getString(R.string.on_click_handler).showSnackbar(binding.root)
            }
        }
    }
}