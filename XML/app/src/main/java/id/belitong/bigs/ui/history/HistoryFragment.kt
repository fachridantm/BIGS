package id.belitong.bigs.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.databinding.FragmentHistoryBinding
import id.belitong.bigs.ui.order.TabOrderAdapter
import id.belitong.bigs.ui.profile.ProfileActivity

@AndroidEntryPoint
class HistoryFragment: BaseFragment<FragmentHistoryBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false)

    override fun initData() {

    }

    override fun initView() {
        val tabOrderAdapter = TabOrderAdapter(activity as AppCompatActivity)
        val viewPager = binding?.viewPagerOrder

        viewPager?.adapter = tabOrderAdapter
        val tabs = binding?.tabLayoutOrder
        if (tabs != null && viewPager != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getText(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun initAction() {
        binding?.toolbarHistory?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btn_profile -> {
                    ProfileActivity.start(requireContext())
                    true
                }
                else -> false
            }
        }
    }

    override fun initObservers() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.viewPagerOrder?.adapter = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_my_order,
            R.string.tab_my_report
        )
    }
}