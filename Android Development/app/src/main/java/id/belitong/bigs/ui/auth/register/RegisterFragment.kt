package id.belitong.bigs.ui.auth.register

import android.os.Bundle
import android.util.Patterns
import android.util.Patterns.PHONE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.utils.showError
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initData() {}

    override fun initView() {}

    override fun initAction() {
        binding?.apply {
            tvLoginHere.setOnClickListener {
                it.findNavController()
                    .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
            btnRegister.setOnClickListener { registerHandler() }
        }
    }

    private fun registerHandler() {
        if (!isFormValid()) return

        val name = binding?.edtNameRegister?.text.toString()
        val email = binding?.edtEmailRegister?.text.toString()
        val password = binding?.edtPasswordRegister?.text.toString()

        registerViewModel.registerUser(name, email, password)
    }

    private fun isFormValid(): Boolean {
        val name = binding?.edtNameRegister?.text.toString()
        val email = binding?.edtEmailRegister?.text.toString()
        val password = binding?.edtPasswordRegister?.text.toString()
        val phone = binding?.edtPhoneRegister?.text.toString()

        binding?.tilNameRegister?.apply {
            if (name.isEmpty()) {
                showError(true, getString(R.string.name_can_not_be_empty))
            } else {
                showError(false)
            }
        }

        binding?.tilEmailRegister?.apply {
            if (email.isEmpty()) {
                showError(true, getString(R.string.email_can_not_be_empty))
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    showError(true, getString(R.string.invalid_email_format))
                } else {
                    showError(false)
                }
            }
        }

        binding?.tilPasswordRegister?.apply {
            if (password.isEmpty()) {
                showError(true, getString(R.string.password_can_not_be_empty))
            } else {
                if (password.length < 8) {
                    showError(true, context.getString(R.string.minimum_password_length))
                } else {
                    showError(false)
                }
            }
        }

        binding?.tilPhoneRegister?.apply {
            if (phone.isEmpty()) {
                showError(true, getString(R.string.phone_can_not_be_empty))
            } else {
                if (!PHONE.matcher(phone).matches()) {
                    showError(true, getString(R.string.invalid_phone_number))
                } else {
                    showError(false)
                }
            }
        }

        return binding?.tilNameRegister?.isErrorEnabled == false &&
                binding?.tilEmailRegister?.isErrorEnabled == false &&
                binding?.tilPasswordRegister?.isErrorEnabled == false &&
                binding?.tilPhoneRegister?.isErrorEnabled == false
    }

    override fun initObservers() {
        registerResult()
    }

    private fun registerResult() {
        registerViewModel.result.observe(this) {
            when (it) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.message?.showMessage(requireContext())
                    binding?.root?.findNavController()
                        ?.navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is Resource.Error -> {
                    showLoading(false)
                    it.message?.showMessage(requireContext())
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.apply {
                pbRegister.visibility = View.VISIBLE
                btnRegister.isEnabled = false
            }
        } else {
            binding?.apply {
                pbRegister.visibility = View.GONE
                btnRegister.isEnabled = true
            }
        }
    }
}