package id.belitong.bigs.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.R
import id.belitong.bigs.core.utils.loadUserImage
import id.belitong.bigs.core.utils.showToast
import id.belitong.bigs.databinding.ActivityProfileBinding
import id.belitong.bigs.databinding.DialogLogoutBinding
import id.belitong.bigs.ui.auth.AuthActivity
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
                getString(R.string.on_click_handler).showToast(this@ProfileActivity)
            }
            btnLanguage.setOnClickListener {
                getString(R.string.on_click_handler).showToast(this@ProfileActivity)
            }
            btnHelp.setOnClickListener {
                getString(R.string.on_click_handler).showToast(this@ProfileActivity)
            }
            btnAbout.setOnClickListener {
                getString(R.string.on_click_handler).showToast(this@ProfileActivity)
            }
            btnLogout.setOnClickListener {
                logoutHandler()
            }
        }
    }

    private fun logoutHandler() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog).create()
        val dialogBinding = DialogLogoutBinding.inflate(layoutInflater)

        dialogBinding.apply {
            btnLogoutYes.setOnClickListener {
                lifecycleScope.launch {
                    AuthActivity.startNewTask(this@ProfileActivity)
                    finish()
                    profileViewModel.deleteSession()
                }
            }
            btnLogoutNo.setOnClickListener {
                builder.dismiss()
            }
        }
        builder.setView(dialogBinding.root)
        builder.show()
    }

    companion object {
        fun start(context: Context) {
            Intent(context, ProfileActivity::class.java).run { context.startActivity(this) }
        }
    }
}