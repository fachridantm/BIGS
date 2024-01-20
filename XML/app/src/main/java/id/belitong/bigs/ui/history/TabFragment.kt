package id.belitong.bigs.ui.history

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
import id.belitong.bigs.core.ui.HistoryTabAdapter
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.databinding.FragmentTabBinding
import id.belitong.bigs.ui.main.MainViewModel

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentTabBinding>() {

    private val historyTabAdapter: HistoryTabAdapter by lazy {
        HistoryTabAdapter(
            ::reportCancelClicked,
            ::orderCancelClicked
        )
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentTabBinding = FragmentTabBinding.inflate(inflater, container, false)

    override fun initData() {
        when (arguments?.getInt(SECTION_NUMBER)) {
            1 -> mainViewModel.getOrders()
            2 -> mainViewModel.getReports()
        }
    }

    override fun initObservers() {
        mainViewModel.orders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    val orderItems = it.data.map { order -> HistoryListItem.OrderItem(order) }
                    historyTabAdapter.submitList(orderItems)
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView(), activity?.findViewById(R.id.nav_view))
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
                    historyTabAdapter.submitList(reportItems)
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView(), activity?.findViewById(R.id.nav_view))
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
                adapter = historyTabAdapter
            }
        }
    }

    override fun initAction() {}


    private fun orderCancelClicked(order: Order) {
        getString(R.string.on_click_handler).showSnackbar(
            requireView(),
            activity?.findViewById(R.id.nav_view)
        )
    }

    private fun reportCancelClicked(report: Report) {
        getString(R.string.on_click_handler).showSnackbar(
            requireView(),
            activity?.findViewById(R.id.nav_view)
        )
    }

    companion object {
        const val SECTION_NUMBER = "id.belitong.bigs.ui.order.section_number"
    }
}