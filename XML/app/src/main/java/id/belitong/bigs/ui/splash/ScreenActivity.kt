package id.belitong.bigs.ui.splash

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.ActivityScreenBinding
import id.belitong.bigs.ui.auth.AuthActivity
import id.belitong.bigs.ui.auth.login.LoginViewModel
import id.belitong.bigs.ui.main.MainActivity

@AndroidEntryPoint
class ScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScreenBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initView()
        initAction()
    }

    private fun initObservers() {
        loginViewModel.token.observe(this@ScreenActivity) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    Log.i("Info", "Token: ${it.data}")
                    if (it.data.isEmpty()) {
                        AuthActivity.start(this@ScreenActivity)
                        finish()
                    } else {
                        MainActivity.start(this@ScreenActivity)
                        finish()
                    }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun initAction() {
        binding.btnExplore.setOnClickListener { loginViewModel.getToken() }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                btnExplore.isClickable = false
                pbScreen.visibility = View.VISIBLE
            } else {
                btnExplore.isClickable = true
                pbScreen.visibility = View.GONE
            }
        }
    }
}