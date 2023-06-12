package id.belitong.bigs.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun initIntent() {}

    override fun initView() {}

    override fun initAction() {
        binding?.apply {
            tvLoginHere.setOnClickListener {
                it.findNavController()
                    .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
            btnRegister.setOnClickListener {}
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    override fun initMenu() {}

}