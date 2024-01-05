package id.belitong.bigs.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.ui.CardOrderAdapter
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.FragmentOrderBinding
import id.belitong.bigs.ui.main.MainViewModel

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>() {

    private val cardOrderAdapter: CardOrderAdapter by lazy { CardOrderAdapter(::cancelClicked) }

    private val mainViewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false)

    override fun initData() {
        val position = arguments?.getInt(SECTION_NUMBER)

        mainViewModel.orders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    cardOrderAdapter.submitList(it.data)
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView())
                }

                else -> {}
            }
        }

        when (position) {
            0 -> mainViewModel.getOrders()
            1 -> mainViewModel.getReports()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                pbOrder.visibility = View.VISIBLE
            } else {
                pbOrder.visibility = View.GONE
            }
        }
    }

    override fun initView() {
        with(binding) {
            this?.rvOrder?.apply {
                setHasFixedSize(true)
                adapter = cardOrderAdapter
            }
        }
    }

    override fun initAction() {

    }

    override fun initObservers() {

    }


    private fun cancelClicked(order: Order) {

    }

    companion object {
        const val SECTION_NUMBER = "id.belitong.bigs.ui.order.section_number"
    }
}