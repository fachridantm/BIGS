package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.databinding.ItemCardOrderBinding
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.utils.HistoryStatus
import id.belitong.bigs.core.utils.loadGeoparkImage
import id.belitong.bigs.core.utils.statusBackgroundFilter

class CardOrderAdapter(
    private val cancelClicked: (Order) -> Unit
) :
    ListAdapter<Order, CardOrderAdapter.OrderViewHolder>(DIFF_CALLBACK) {

    inner class OrderViewHolder(private val binding: ItemCardOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Order) {
            binding.apply {
                itemTvTitleGeositeOrder.text = data.geositeName
                itemIvOrderGeosite.loadGeoparkImage(data.geositeImage)
                itemTvTourGuideName.text = data.tourGuideName
                itemTvTourGuidePhone.text = data.tourGuidePhone
                itemTvBookingDate.text = data.bookingDate
                itemTvTourDate.text = data.tourDate
                itemTvProgramName.text = data.programName
                itemTvStatus.statusBackgroundFilter(data.status)

                if (data.status == HistoryStatus.COMPLETED.value || data.status == HistoryStatus.CANCELLED.value) {
                    btnCancel.visibility = ViewGroup.GONE
                } else {
                    btnCancel.visibility = ViewGroup.VISIBLE
                    btnCancel.setOnClickListener { cancelClicked(data) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            ItemCardOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        if (order != null) {
            holder.bind(order)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem.id == newItem.id
        }
    }
}