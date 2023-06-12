package id.belitong.bigs.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initIntent() {}

    override fun initView() {}

    override fun initAction() {
        binding?.apply {
            tvRegisterHere.setOnClickListener {
                it.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            btnLogin.setOnClickListener {}
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    override fun initMenu() {}

}