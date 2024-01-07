package id.belitong.bigs.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.HistoryListItem
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.model.Report
import id.belitong.bigs.core.ui.CardOrderAdapter
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.FragmentOrderBinding
import id.belitong.bigs.ui.main.MainViewModel

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>() {

    private val cardOrderAdapter: CardOrderAdapter by lazy {
        CardOrderAdapter(
            ::reportCancelClicked,
            ::orderCancelClicked
        )
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false)

    override fun initData() {
        when (arguments?.getInt(SECTION_NUMBER)) {
            0 -> mainViewModel.getOrders()
            1 -> mainViewModel.getReports()
        }
    }

    override fun initObservers() {
        mainViewModel.orders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    val orderItems = it.data.map { order -> HistoryListItem.OrderItem(order) }
                    cardOrderAdapter.submitList(orderItems)
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView(), navView)
                }

                else -> {}
            }
        }

        mainViewModel.reports.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    val reportItems = it.data.map { report -> HistoryListItem.ReportItem(report) }
                    cardOrderAdapter.submitList(reportItems)
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView(), navView)
                }

                else -> {}
            }
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

    override fun initAction() {}


    private fun orderCancelClicked(order: Order) {
        getString(R.string.on_click_handler).showSnackbar(requireView(), navView)
    }

    private fun reportCancelClicked(report: Report) {
        getString(R.string.on_click_handler).showSnackbar(requireView(), navView)
    }

    companion object {
        const val SECTION_NUMBER = "id.belitong.bigs.ui.order.section_number"
    }
}