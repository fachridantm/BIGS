package id.belitong.bigs.ui.details.geoprogramme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.databinding.ActivityGeoprogrammeBinding

@AndroidEntryPoint
class GeoprogrammeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeoprogrammeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeoprogrammeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO 4: Implement GeoprogrammeActivity
    }
}