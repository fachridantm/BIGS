package id.belitong.bigs.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.utils.loadUserImage
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.ActivityProfileBinding
import id.belitong.bigs.ui.auth.AuthActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initAction()
    }

    private fun initData() {
        val email = profileViewModel.getEmail()
        val phone = profileViewModel.getPhoneNumber()
        val photo = profileViewModel.getUserImage()

        binding.apply {

            profileViewModel.getName().observe(this@ProfileActivity) { name ->
                tvName.text = name
            }

            tvEmail.text = email
            tvPhone.text = phone
            ivProfile.loadUserImage(photo)
        }
    }

    private fun initView() {
        binding.toolbarProfile.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initAction() {
        with(binding) {
            btnEditProfile.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(this@ProfileActivity)
            }
            btnLanguage.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(this@ProfileActivity)
            }
            btnHelp.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(this@ProfileActivity)
            }
            btnAbout.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(this@ProfileActivity)
            }
            btnLogout.setOnClickListener {
                logoutHandler()
            }
        }
    }

    private fun logoutHandler() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog).create()
        val view = layoutInflater.inflate(R.layout.view_logout_dialog, null)
        val btnYes = view.findViewById<AppCompatButton>(R.id.btn_logout_yes)
        val btnNo = view.findViewById<AppCompatButton>(R.id.btn_logout_no)
        btnYes.setOnClickListener {
            lifecycleScope.launch {
                profileViewModel.deleteSession()
                delay(500)
                val loginIntent = Intent(this@ProfileActivity, AuthActivity::class.java)
                loginIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(loginIntent)
                finish()
            }
        }
        btnNo.setOnClickListener {
            builder.dismiss()
        }
        builder.setView(view)
        builder.show()
    }
}