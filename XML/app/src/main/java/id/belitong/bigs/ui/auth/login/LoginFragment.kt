package id.belitong.bigs.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.User
import id.belitong.bigs.core.utils.showError
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.FragmentLoginBinding
import id.belitong.bigs.ui.main.MainActivity

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initData() {}

    override fun initView() {}

    override fun initAction() {
        binding?.apply {
            tvForgotPassword.setOnClickListener {
                requireContext().getString(R.string.on_click_handler).showMessage(requireContext())
            }
            tvRegisterHere.setOnClickListener {
                it.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            btnLogin.setOnClickListener { loginHandler() }
            btnGoogleLogin.setOnClickListener {
                requireContext().getString(R.string.on_click_handler).showMessage(requireContext())
            }
        }
    }

    private fun loginHandler() {
        if (!isFormValid()) return

        val email = binding?.edtEmailLogin?.text.toString()
        val password = binding?.edtPasswordLogin?.text.toString()

        loginViewModel.loginUser(email, password)
    }

    private fun isFormValid(): Boolean {
        val email = binding?.edtEmailLogin?.text.toString()
        val password = binding?.edtPasswordLogin?.text.toString()

        binding?.tilEmailLogin?.apply {
            if (email.isEmpty()) {
                showError(true, getString(R.string.email_not_allowed_to_be_empty))
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    showError(true, getString(R.string.invalid_email_format))
                } else {
                    showError(false)
                }
            }
        }

        binding?.tilPasswordLogin?.apply {
            if (password.isEmpty()) {
                showError(true, getString(R.string.password_not_allowed_to_be_empty))
                errorIconDrawable = null
            } else {
                if (password.length < 8) {
                    showError(true, context.getString(R.string.minimum_password_length))
                    errorIconDrawable = null
                } else {
                    showError(false)
                }
            }
        }

        return binding?.tilEmailLogin?.isErrorEnabled == false &&
                binding?.tilPasswordLogin?.isErrorEnabled == false
    }

    override fun initObservers() {
        loginResult()
    }

    private fun loginResult() {
        loginViewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    val token = it.data?.loginResult?.token
                    val user = it.data?.loginResult
                    val message = it.data?.message
                    showLoading(false)
                    if (token != null && user != null) {
                        saveSession(token, user)
                    }
                    getString(R.string.login_result, message).showMessage(requireContext())
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message?.showMessage(requireContext())
                }
            }
        }
    }

    private fun saveSession(token: String, user: User) {
        loginViewModel.saveSession(token, user)
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                pbLogin.visibility = View.VISIBLE
                btnLogin.isEnabled = false
                btnGoogleLogin.isEnabled = false
            } else {
                pbLogin.visibility = View.GONE
                btnLogin.isEnabled = true
                btnGoogleLogin.isEnabled = true
            }
        }
    }
}