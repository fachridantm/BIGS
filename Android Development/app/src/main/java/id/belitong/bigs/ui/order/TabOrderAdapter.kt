package id.belitong.bigs.ui.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.belitong.bigs.ui.order.OrderFragment.Companion.SECTION_NUMBER

class TabOrderAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = OrderFragment()
        fragment.arguments = Bundle().apply {
            putInt(SECTION_NUMBER, position + 1)
        }
        return fragment
    }
}