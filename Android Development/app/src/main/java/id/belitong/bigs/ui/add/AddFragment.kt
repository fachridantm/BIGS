package id.belitong.bigs.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.databinding.FragmentAddBinding

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initAction() {

    }

    override fun initObservers() {

    }
}